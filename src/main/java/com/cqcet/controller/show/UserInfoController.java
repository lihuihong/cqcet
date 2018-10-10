package com.cqcet.controller.show;

import com.cqcet.services.ArticleService;
import com.cqcet.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by 论坛 on 2018/9/11.
 * 帖子
 */
@Controller
@RequestMapping(value = "/show/user",method={RequestMethod.GET})
public class UserInfoController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TypeService typeService;

    /**
     * 查询所有帖子
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/user.action")
    public String user(ModelMap map,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {

        return "show/user";
    }

    @RequestMapping("/dashboard.action")
    public String index(){
        return "show/dashboard";
    }

    /**
     *退出登录
     * @param session
     * @return
     */
    @RequestMapping("/login_out.action")
    public String loginOut(HttpSession session) {
        //销毁session
        session.invalidate();
        return "show/index";
    }


}
