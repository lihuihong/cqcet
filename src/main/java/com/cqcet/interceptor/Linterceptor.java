package com.cqcet.interceptor;

import com.cqcet.entity.EntityType;
import com.cqcet.entity.Message;
import com.cqcet.entity.User;
import com.cqcet.services.JedisAdapter;
import com.cqcet.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/9/11.
 * 拦截器
 */
public class Linterceptor implements HandlerInterceptor {

    @Autowired
    MessageService messageService;
    @Autowired
    JedisAdapter jedisAdapter;

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        //获取请求的地址ַ
        String url = request.getRequestURI();
        //  对特殊地址，直接放行
        if (url.indexOf("show") > 0 || url.indexOf("resources") > 0 || url.indexOf("upload") > 0 || url.indexOf("index") > 0) {
            return true;
        }
        //判断session，session存在的话，登录后台.getSession();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            List<Message> conversationList = messageService.getConversationList(Integer.parseInt(user.getId()));
            int unreadCount = 0;
            for (Message msg : conversationList) {
                //未读消息
                int count = messageService.getConvesationUnreadCount(Integer.parseInt(msg.getToId()), msg.getConversationId());
                unreadCount = unreadCount + count;
            }
            //未读消息放在redis
            messageService.message(Integer.parseInt(user.getId()), EntityType.ENTITY_MESSAGE,unreadCount);
            session.setAttribute("unreadCount",unreadCount);
            //身份存在，放行
            return true;
        }
        //ִ 执行这里表示用户身份需要验证，跳转到登录界面
        request.getRequestDispatcher("/WEB-INF/view/show/login.jsp").forward(request, response);
        return false;
    }
}
