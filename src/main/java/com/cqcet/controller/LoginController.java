package com.cqcet.controller;


import com.cqcet.entity.Article;
import com.cqcet.entity.Result;
import com.cqcet.entity.Type;
import com.cqcet.services.ArticleService;
import com.cqcet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zcq1314 on 2018/9/13.
 */
@Controller
@RequestMapping("/show")
public class LoginController {


    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    /**
     * 登录界面跳转
     */
    @RequestMapping("/login.action")
    public String to_login(HttpServletRequest request){
        request.setAttribute("select","login");
        request.setAttribute("refererUrl",request.getHeader("Referer"));
        return "show/login";
    }

    /**
     * 注册界面跳转
     */
    @RequestMapping("/register.action")
    public String to_register(HttpServletRequest request){
        request.setAttribute("select","register");
        request.setAttribute("refererUrl",request.getHeader("Referer"));
        return "show/login";
    }

    /**
     * 用户登录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login.json",method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request) throws Exception {

        Map<String, Object> info = userService.selectUser(request);

        return Result.success().add("info", info);
    }

    /**
     * 用户注册
     * @throws Exception
     */
    @RequestMapping(value = "/register.json",method = RequestMethod.POST)
    @ResponseBody
    public Result show_register(HttpServletRequest request) throws Exception {

        Map<String, Object> info = userService.show_regist(request);

        return Result.success().add("info", info);
    }
}
