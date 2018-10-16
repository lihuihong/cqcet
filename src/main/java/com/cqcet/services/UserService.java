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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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


    /**
     * 用户登录
     * @param request
     * @return
     * @throws LException
     * @throws UnsupportedEncodingException
     */
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

        //更新最后一次登陆时间
        Date currentTime = new Date();
        user.setLastLoginTime(currentTime);
        userMapper.update(user);


        //将用户信息保存进session
        request.getSession().setAttribute("userInfo", user);
        request.getSession().setAttribute("user", user.getId());
        request.getSession().setAttribute("avatar", user.getAvatar());
        request.getSession().setAttribute("username", user.getUsername());

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
     * 查询全部用户(条件查询)
     *
     * @param param
     * @return
     */
    public List<User> list(Map<String, Object> param) {
        return userMapper.list(param);
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    public List<User> list_article() {
        return userMapper.listArticle();
    }

    /**
     * 根据用户id查询信息
     * @param id
     * @return
     */
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据用户id更新用户扩展信息
     * @param userInfo
     * @return
     */
    public void updateByUserInfo(UserInfo userInfo) {
       userInfoMapper.update(userInfo);
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

            if (studentId.length() < 2 || studentId.length() > 11) {
                throw new LException("学号长度应该是2到10个");
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
            /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formatter.format(currentTime);*/

            // 注册时间
            if (StringUtils.isEmpty(user.getRegisterTime())) {
                user.setRegisterTime(currentTime);
            }
            // 上次访问时间
            if (StringUtils.isEmpty(user.getLastLoginTime())) {
                user.setLastLoginTime(currentTime);
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
        String professional = request.getParameter("professional");

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
        // 校验专业是否存在
        count = professionalMapper.countByName(professional, null);
        if (count == 0) {
            throw new LException("专业不存在");
        }

        //添加用户
        Date currentTime = new Date();
       /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(currentTime);*/

        User user = new User();
        // 注册时间
        if (StringUtils.isEmpty(user.getRegisterTime())) {
            user.setRegisterTime(currentTime);
        }
        // 上次访问时间
        if (StringUtils.isEmpty(user.getLastLoginTime())) {
            user.setLastLoginTime(currentTime);
        }
        user.setPassword(MD5.md5(password));    // 密码加密
        user.setStatus("0");    // 正常状态
        int collegeId = collegeMapper.idByName(college);
        //int collegeId = professionalMapper.selectByProfessionalId(user.getProfessionalId());
        user.setCollege(String.valueOf(collegeId));
        user.setLevel("1");
        user.setUsername(username);
        user.setStudentId(studentId);
        userMapper.insert(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setGradeId("1");
        userInfo.setAvatar("http://heylhh.com/FgWPzwwYEQRoBYYvx1lL3epPtIws");
        int professionalId = professionalMapper.idByName(professional);
        userInfo.setProfessionalId(String.valueOf(professionalId));
        userInfoMapper.insert(userInfo);

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

    /**
     * 用户修改密码
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     */
    public void updateNewPassword(HttpServletRequest request,String oldPassword, String newPassword1, String newPassword2) throws LException {
        //得到当前用户登录的id
        String userId = String.valueOf(request.getSession().getAttribute("user"));
        if (oldPassword==null &&oldPassword == ""){
            throw new LException("输入旧密码不能为空");
        }
        if (newPassword1==null &&newPassword1 == ""){
            throw new LException("输入新密码不能为空");
        }
        if (newPassword2==null &&newPassword2 == ""){
            throw new LException("再次输入新密码不能为空");
        }
        User user1 = getUserInfo(request);
        //根据旧密码，判断查询用户
        User user = userMapper.selectByPassword(MD5.md5(oldPassword),user1.getId());
        if (user == null){
            throw new LException("旧密码错误");
        }
        //校验新密码
        newPassword1 = newPassword1.replaceAll("\\s*", "");
        if (newPassword1.length()<6 || newPassword1.length()>16) {
            throw new LException("密码长度应为6到16个字符");
        }
        //校验新密码两次输入是否一致
        if (!newPassword1.equals(newPassword2)) {
            throw new LException("两次新密码不一致");
        }
        //更新新密码
        user.setPassword(MD5.md5(newPassword1));
        user.setId(userId);
        userMapper.update(user);

    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public User getUserInfo(HttpServletRequest request) {
        // 从session中取用户信息
        // 判断session
        HttpSession session  = request.getSession();
        // 从session中取出用户身份信息
        User user = (User)session.getAttribute("userInfo");
        if (user!=null) {
            request.getSession().setAttribute("userInfo", user);
            request.getSession().setAttribute("user", user.getId());
            request.getSession().setAttribute("avatar", user.getAvatar());
            request.getSession().setAttribute("username", user.getUsername());
            User user1 = selectById(user.getId());
            // 根据主键查询用户信息
            return user1;
        }
        // session失效时，获取cookie
        String userToken = "";
        Cookie[] cookieArr = request.getCookies();
        if (cookieArr!=null && cookieArr.length>0) {
            for (int i=0; i<cookieArr.length; i++) {
                Cookie cookie = cookieArr[i];
                if ("userToken".equals(cookie.getName())) {
                    try {
                        userToken = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        break;
                    }
                    break;
                }
            }
        }
        // 指定cookie不存在时，直接返回null
        if (StringUtils.isEmpty(userToken)) {
            return null;
        }

        // 指定cookie存在时，模拟登录，获取用户信息
        try {
            user = getUserInfoByUserToken(userToken);
            // 将用户信息保存进session
            request.getSession().setAttribute("userInfo", user);
            request.getSession().setAttribute("user", user.getId());
            request.getSession().setAttribute("avatar", user.getAvatar());
            request.getSession().setAttribute("username", user.getUsername());
} catch (LException e) {
        return null;
        }

        return user;
        }

/**
 * 更新用户session
 * @param request
 * @return
 */
    public User updateUserInfoSession(HttpServletRequest request) {
        User userInfo = null;
        // 获取cookie
        String userToken = "";
        Cookie[] cookieArr = request.getCookies();
        if (cookieArr!=null && cookieArr.length>0) {
            for (int i=0; i<cookieArr.length; i++) {
                Cookie cookie = cookieArr[i];
                if ("userToken".equals(cookie.getName())) {
                    try {
                        userToken = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        break;
                    }
                    break;
                }
            }
        }

        // 根据userToken重新获取用户信息
        // 指定cookie不存在时，直接返回null
        if (StringUtils.isEmpty(userToken)) {
            return null;
        }

        // 指定cookie存在时，模拟登录，获取用户信息
        try {
            userInfo = getUserInfoByUserToken(userToken);
            // 将用户信息保存进session
            request.getSession().setAttribute("userInfo", userInfo);
            request.getSession().setAttribute("user", userInfo.getId());
            request.getSession().setAttribute("avatar", userInfo.getAvatar());
            request.getSession().setAttribute("username", userInfo.getUsername());
        } catch (LException e) {
            // 用户凭证是伪造的
            return null;
        }

        return userInfo;
    }


    /**
     * 根据userToken，自动登录
     * @param userToken
     * @return
     * @throws LException
     */
    public User getUserInfoByUserToken(String userToken) throws LException {
        // userToken编码转换
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decoderBase64;
        try {
            decoderBase64 = decoder.decodeBuffer(userToken);
            userToken = new String(decoderBase64);
        } catch (IOException e) {
            throw new LException("未登录");
        }

        String[] arr = userToken.split("&&");
        if (arr.length<=1) {
            throw new LException("未登录");
        }

        // userToken解密
        String username = Jiami.getInstance().decrypt(arr[0]);
        String password = Jiami.getInstance().decrypt(arr[1]);

        // 自动登陆
        User  user1= selectUser(username, MD5.md5(password));
        // 根据主键查询用户信息
        User user = selectById(user1.getId());
        if (user==null) {
            throw new LException("未登录");
        }
        return user;
    }

}
