package com.cqcet.controller;


import com.cqcet.entity.User;
import com.cqcet.exception.LException;
import com.cqcet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by 那个谁 on 2018/9/11.
 * 首页
 */
@Controller
@RequestMapping(value = "/show/index",method={RequestMethod.GET})
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * 主页
     * @param map
     * @return
     */
    @RequestMapping("/index.action")
    public String index(ModelMap map, HttpServletRequest request) throws LException {
        User userInfo = userService.getUserInfo(request);
        if (userInfo==null) {
            return "/show/login";
        }
        return "show/index";
    }

}
