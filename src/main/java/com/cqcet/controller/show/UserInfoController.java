package com.cqcet.controller.show;

import com.cqcet.entity.Article;
import com.cqcet.entity.Result;
import com.cqcet.entity.User;
import com.cqcet.entity.UserInfo;
import com.cqcet.exception.LException;
import com.cqcet.services.ArticleService;
import com.cqcet.services.TypeService;
import com.cqcet.services.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 论坛 on 2018/9/11.
 * 帖子
 */
@Controller
@RequestMapping(value = "/show/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeService typeService;

    /**
     * 个人信息中心
     * @param map
     * @return
     */
    @RequestMapping("/user.action")
    public String user(ModelMap map, HttpServletRequest request) {

        //得到当前用户登录的信息
        User user = userService.getUserInfo(request);
        if (user==null) {
            // 非法访问，重定向到登录页
            return "redirect:login.action";
        }
        map.put("user",user);
        return "show/user";
    }

    /**
     * 个人信息页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/dashboard.action")
    public String index(ModelMap map,HttpServletRequest request) throws ParseException {
        //得到当前用户登录的id
        User user = userService.getUserInfo(request);
        if (user==null) {
            // 非法访问，重定向到登录页
            return "redirect:/show/login.action";
        }
        map.put("user",user);
        return "show/dashboard";
    }

    /**
     * 保存修改密码
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     * @return
     * @throws LException
     */
    @RequestMapping(value = "/save_password.json",method={RequestMethod.POST})
    @ResponseBody
    public Result save(HttpServletRequest request,
                       @RequestParam(value="oldPassword") String oldPassword,
                       @RequestParam(value="newPassword1") String newPassword1,
                       @RequestParam(value="newPassword2") String newPassword2) throws LException {
        userService.updateNewPassword(request,oldPassword.trim(),newPassword1.trim(),newPassword2.trim());

        return Result.success();
    }

    /**
     * 个人帖子中心
     * @return
     */
    @RequestMapping("/postCenter.action")
    public String postCenter(ModelMap map, HttpServletRequest request,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        //得到当前用户登录的id
        String userId = String.valueOf(request.getSession().getAttribute("user"));

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",userId);
        param.put("status", 0);

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);
        User user = userService.getUserInfo(request);
        map.put("user",user);
        // 查询所有文章分类
        map.put("typeList", typeService.list());
        return "show/postCenter";
    }

    /**
     * 修改头像
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("head_avatar.action")
    public String headAvatar(ModelMap map, HttpServletRequest request) {
        User user = userService.getUserInfo(request);
        if (user==null) {
            // 非法访问，重定向到登录页
            return "redirect:/show/login.action";
        }
        map.put("user",user);
        return "show/head";
    }

    /**
     *退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/login_out.json",method={RequestMethod.POST})
    @ResponseBody
    public Result loginOut(HttpSession session) {
        //销毁session
        session.invalidate();
        return Result.success();
    }


}
