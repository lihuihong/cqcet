package com.cqcet.controller.show;

import com.cqcet.entity.Result;
import com.cqcet.entity.User;
import com.cqcet.services.ArticleService;
import com.cqcet.services.TypeService;
import com.cqcet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 论坛 on 2018/9/11.
 * 帖子
 */
@Controller
@RequestMapping(value = "/show/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    /**
     * 个人信息中心
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

    //修改信息
    @RequestMapping("/dashboard.action")
    public String index(ModelMap map,@RequestParam(value = "userId") String userId){
        User user = userService.selectById(userId);
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
                       @RequestParam(value="newPassword2") String newPassword2) throws LException{
        userService.updateNewPassword(request,oldPassword.trim(),newPassword1.trim(),newPassword2.trim());

        return Result.success();
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
