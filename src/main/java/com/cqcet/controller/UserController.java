package com.cqcet.controller;

import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.ProfessionalService;
import com.cqcet.services.UserGradeService;
import com.cqcet.services.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/9/11.
 * 用户登陆
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private ProfessionalService professionalService;

    /**
     * 登陆验证
     * @param map
     * @param request
     * @return
     * @throws LException
     */
    @RequestMapping("/login.json")
    @ResponseBody
    public Result login(ModelMap map, HttpServletRequest request) throws LException {
        // 获取参数
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        // 校验参数
        // 判断参数是否为空
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            throw new LException("用户名、密码不能为空");
        }
        //  判断用户名密码是否正确
        User user = userService.selectUser(userName, passWord);
        if (user == null) {
            throw new LException("用户名或密码不正确");
        }

        // 设置session
        request.getSession().setAttribute("user", user);

        return Result.success();
    }

    /**
     * 查询全部用户(正常)
     * @param map
     * @param groupId
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list_normal.action")
    public String list_normal(ModelMap map,
                              @RequestParam(required=false, value="groupId") String groupId,
                              @RequestParam(required=false, value="keyword") String keyword,
                              @RequestParam(value="pageNum", defaultValue="1") int pageNum,
                              @RequestParam(value="pageSize", defaultValue="10") int pageSize){
        Map<String,Object> param = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(groupId)){
            param.put("groupId",groupId);
            map.put("groupId",groupId);
        }
        if (!StringUtils.isEmpty(keyword)){
            param.put("keyword",keyword);
            map.put("keyword",keyword);
        }

        param.put("status",0);
        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.list(param);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        map.put("pageInfo", pageInfo);

        // 查询所有用户组
        List<UserGrade> gradeList = userGradeService.list();
        map.put("gradeList", gradeList);

        return "admin/user/list_normal";
    }

    /**
     * 查询全部用户(封禁)
     * @param map
     * @param groupId
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list_ban.action")
    public String list_ban(ModelMap map,
                          @RequestParam(required=false, value="groupId") String groupId,
                          @RequestParam(required=false, value="keyword") String keyword,
                          @RequestParam(value="pageNum", defaultValue="1") int pageNum,
                          @RequestParam(value="pageSize", defaultValue="10") int pageSize) {

        Map<String, Object> param = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(groupId)) {
            param.put("groupId", groupId);
            map.put("groupId", groupId);
        }
        if (!StringUtils.isEmpty(keyword)) {
            param.put("keyword", keyword.trim());
            map.put("keyword", keyword);
        }
        param.put("status", "1");

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.list(param);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        map.put("pageInfo", pageInfo);

        // 查询所有用户组
        List<UserGrade> gradeList = userGradeService.list();
        map.put("gradeList", gradeList);

        return "admin/user/list_ban";
    }

    /**
     * 编辑用户
     * @param map
     * @param id
     * @return
     */
    @RequestMapping("/edit.action")
    public String edit(ModelMap map,
                       @RequestParam(required = false,value = "id") String id){
        //查询单个用户信息
        if (!StringUtils.isEmpty(id)){
            User user = userService.selectById(id);
            map.put("editUserInfo", user);
        }
        // 查询所有用户组
        List<UserGrade> gradeList = userGradeService.list();
        map.put("gradeList", gradeList);
        //查询所有专业
        List<Professional> professionalList = professionalService.list();
        map.put("professionalList",professionalList);
        map.put("id", id);
        return "admin/user/edit";

    }

    /**
     * 保存用户信息
     * @param user
     * @return
     * @throws LException
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public Result save(User user) throws LException{
        userService.save(user);
        return Result.success();
    }

    /**
     * 批量变换用户组
     * @param idArr
     * @param groupId
     * @return
     */
    @RequestMapping("/batch_change_group.json")
    @ResponseBody
    public Result batchChangeGroup(
            @RequestParam(value = "idArr") String[] idArr,
            @RequestParam(value = "groupId") String groupId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userIdArr", idArr);
        param.put("gradeId", groupId);

        userService.batchChangeGroup(param);

        return Result.success();
    }


    /**
     * 批量更新用户状态
     * @param idArr
     * @param status
     * @return
     */
    @RequestMapping("batch_update_status.json")
    @ResponseBody
    public Result batchUpdateStatus(
            @RequestParam(value = "idArr") String[] idArr,
            @RequestParam(value = "status") String status) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("idArr", idArr);
        param.put("status", status);

        userService.batchUpdateStatus(param);

        return Result.success();
    }


    /**
     *用户注册
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/regist.action")
    public String regist(User user,Model model){

        user.setStatus("0");
        userService.regist(user);
        model.addAttribute("msg", "注册成功");
        //注册成功后跳转success.jsp页面
        return "/login";
    }

    /**
     *退出登录
     * @param session
     * @return
     */
    @RequestMapping("login_out.action")
    public String loginOut(HttpSession session) {
        //销毁session
        session.invalidate();
        return "/login";
    }

}

