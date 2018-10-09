package com.cqcet.services;

import com.cqcet.dao.CollegeMapper;
import com.cqcet.dao.ProfessionalMapper;
import com.cqcet.dao.UserInfoMapper;
import com.cqcet.dao.UserMapper;
import com.cqcet.entity.User;
import com.cqcet.entity.UserInfo;
import com.cqcet.exception.LException;
import com.cqcet.util.Jiami;
import com.cqcet.util.MD5;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import javafx.print.PaperSource;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/9/11.
 */
@Service("UserService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Autowired
    private CollegeMapper collegeMapper;


    public Map<String, Object> selectUser(HttpServletRequest request) throws LException, UnsupportedEncodingException {

        //获取登录参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //校验用户名、密码是否正确
        User user  =selectUser(username,MD5.md5(password));
        if (user == null){
            throw new LException("账号或密码错误");
        }

        //校验用户是否正常
        if ("1".equals(user.getStatus())) {
            throw new LException("该账号已被管理员封禁");
        }

        //将用户信息保存进session
        request.getSession().setAttribute("userInfo", user);

        //对用户信息进行加密，用于cookie存储
        // 用户的登录名和密码
        String userToken = Jiami.getInstance().encrypt(username) + "&&" + Jiami.getInstance().encrypt(password);
        // 将用户名转为没有特殊字符的base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        userToken = encoder.encode(userToken.getBytes());

        Map<String, Object> info = new HashMap<String, Object>();
        info.put("userToken", userToken);

        return info;
    }

    /**
     * 校验用户登录
     * @param username 登录名
     * @param password 登录密码
     * @return
     */
    public User selectUser(String username, String password) {
        return userMapper.checkUser(username, password);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public void regist(User user) {
        userMapper.regist(user);
    }

    /**
     * 查询全部用户
     *
     * @param param
     * @return
     */
    public List<User> list(Map<String, Object> param) {
        return userMapper.list(param);
    }

    /**
     * 根据用户id查询信息
     *
     * @param id
     * @return
     */
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @throws LException
     */
    public void save(User user) throws LException {
        if (StringUtils.isEmpty(user.getId())) {
            //新增
            String password = user.getPassword();
            String studentId = String.valueOf(user.getStudentId());
            String username = user.getUsername();

            // 校验用户名是否填写
            if (StringUtils.isEmpty(username)) {
                throw new LException("用户名不能为空");
            }

            // 校验学号是否填写
            if (StringUtils.isEmpty(studentId)) {
                throw new LException("学号不能为空");
            }
            // 校验学号长度
            studentId = studentId.replaceAll("\\s*", "");
            ;
            if (studentId.length() < 2 || studentId.length() > 6) {
                throw new LException("学号长度应该是2到6个");
            }
            // 校验密码是否填写
            if (StringUtils.isEmpty(password)) {
                throw new LException("密码不能为空");
            }
            // 校验密码长度
            password = password.replaceAll("\\s*", "");
            if (password.length() < 6 || password.length() > 16) {
                throw new LException("密码长度应该是6到16个");
            }

            int count = 0;
            // 校验学号是否已被占用
            count = countUser(studentId, null);
            if (count > 0) {
                throw new LException("该学号已被使用");
            }
            // 校验用户名是否已被占用
            count = countUser(null, username);
            if (count > 0) {
                throw new LException("该用户名已被使用");
            }

            //添加用户
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = formatter.format(currentTime);

            // 注册时间
            if (StringUtils.isEmpty(user.getRegisterTime())) {
                user.setRegisterTime(now);
            }
            // 上次访问时间
            if (StringUtils.isEmpty(user.getLastLoginTime())) {
                user.setLastLoginTime(now);
            }
            user.setPassword(MD5.md5(password));    // 密码加密
            user.setStatus("0");    // 正常状态
            int collegeId = professionalMapper.selectByProfessionalId(user.getProfessionalId());
            user.setCollege(String.valueOf(collegeId));
            user.setLevel(user.getGroupId());
            userMapper.insert(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setGradeId(user.getGroupId());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setProfessionalId(user.getProfessionalId());
            userInfoMapper.insert(userInfo);

        } else {
            // 编辑
            if (!StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(MD5.md5(user.getPassword()));    // 密码加密
            }
            int collegeId = professionalMapper.selectByProfessionalId(user.getProfessionalId());
            user.setCollege(String.valueOf(collegeId));
            user.setLevel(user.getGroupId());
            userMapper.update(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setGradeId(user.getGroupId());
            userInfo.setProfessionalId(user.getProfessionalId());

            userInfoMapper.update(userInfo);
        }

    }

    /**
     * 校验学号或用户名是否已被占用
     *
     * @param studentId
     * @param username
     * @return
     */
    private int countUser(String studentId, String username) {
        return userMapper.countUser(studentId, username);
    }

    /**
     * 批量变换用户组
     *
     * @param param
     */
    public void batchChangeGroup(Map<String, Object> param) {
        userInfoMapper.batchUpdate(param);
    }

    /**
     * 批量更新用户状态
     *
     * @param param
     */
    public void batchUpdateStatus(Map<String, Object> param) {
        userMapper.batchUpdate(param);
    }

    /**
     * 用户注册
     * @param request
     * @return
     * @throws LException
     */
    public Map<String, Object> show_regist(HttpServletRequest request) throws LException {

        //获取注册参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String studentId = request.getParameter("studentId");
        String college = request.getParameter("college");

        int count = 0;
        // 校验学号是否已被占用
        count = countUser(studentId, null);
        if (count > 0) {
            throw new LException("该学号已被注册");
        }
        // 校验用户名是否已被占用
        count = countUser(null, username);
        if (count > 0) {
            throw new LException("该用户名已被占用");
        }
        // 校验学院是否存在
        count = collegeMapper.countByName(college, null);
        if (count == 0) {
            throw new LException("学院不存在");
        }

        //添加用户
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(currentTime);

        User user = new User();
        // 注册时间
        if (StringUtils.isEmpty(user.getRegisterTime())) {
            user.setRegisterTime(now);
        }
        // 上次访问时间
        if (StringUtils.isEmpty(user.getLastLoginTime())) {
            user.setLastLoginTime(now);
        }
        user.setPassword(MD5.md5(password));    // 密码加密
        user.setStatus("0");    // 正常状态
        int collegeId = collegeMapper.idByName(college);
        //int collegeId = professionalMapper.selectByProfessionalId(user.getProfessionalId());
        user.setCollege(String.valueOf(collegeId));
        user.setLevel("0");
        user.setUsername(username);
        user.setStudentId(studentId);
        userMapper.insert(user);

        /*UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setGradeId(user.getGroupId());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setProfessionalId(user.getProfessionalId());
        userInfoMapper.insert(userInfo);
        */
        // 对用户信息进行加密，用于cookie存储
        // 用户的登录名和密码
        String userToken = Jiami.getInstance().encrypt(username) + "&&" + Jiami.getInstance().encrypt(password);
        // 将用户名转为没有特殊字符的base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        userToken = encoder.encode(userToken.getBytes());

        Map<String, Object> info = new HashMap<String, Object>();
        info.put("userToken", userToken);

        return info;

    }
}
