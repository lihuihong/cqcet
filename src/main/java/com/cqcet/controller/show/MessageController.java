package com.cqcet.controller.show;

import com.cqcet.entity.Message;
import com.cqcet.entity.Result;
import com.cqcet.entity.User;
import com.cqcet.entity.ViewObject;
import com.cqcet.exception.LException;
import com.cqcet.services.MessageService;
import com.cqcet.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 站内信
 * Created by 那个谁 on 2018/10/25.
 */
@Controller
@RequestMapping(value = "/show")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    /**
     * 发消息
     * @param map
     * @param request
     * @param toName
     * @param content
     * @return
     * @throws LException
     */
    @RequestMapping(value = "/msg/addMessage", method = {RequestMethod.POST})
    @ResponseBody
    public Result addMessage(ModelMap map,HttpServletRequest request,
                             @RequestParam("toName") String toName,
                             @RequestParam("content") String content) throws LException {

            User userInfo = userService.getUserInfo(request);
            if (userInfo == null) {
                return Result.error("未登录");
            }
            User user = userService.selectUser(toName,null);
            if (user == null) {
                throw new LException("用户不存在");
            }
            Message msg = new Message();
            msg.setContent(content);
            int fromId = Integer.parseInt(userInfo.getId());
            msg.setFromId(fromId);
            int toId = Integer.parseInt(user.getId());
            msg.setToId(toId);
            msg.setCreatedDate(new Date());
            msg.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId) : String.format("%d_%d", toId, fromId));
            messageService.addMessage(msg);
            return Result.success();
    }

    /**
     * 消息列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/msg/list", method = {RequestMethod.GET})
    public String conversationDetail(ModelMap map,HttpServletRequest request) {
            User userInfo = userService.getUserInfo(request);
            if (userInfo == null) {
                return "/show/login";
            }
            int localUserId = Integer.parseInt(userInfo.getId());
            List<ViewObject> conversations = new ArrayList<ViewObject>();
            List<Message> conversationList = messageService.getConversationList(localUserId);
            for (Message msg : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("conversation", msg);
                String targetId = String.valueOf(msg.getFromId() == localUserId ? msg.getToId() : msg.getFromId());
                User user = userService.selectById(targetId);
                vo.set("user", user);
                vo.set("unread", messageService.getConvesationUnreadCount(localUserId, msg.getConversationId()));
                conversations.add(vo);
            }
            map.put("conversations", conversations);
        return "";
    }

    /**
     * 消息详情
     * @param map
     * @param conversationId
     * @return
     */
    @RequestMapping(value = "/msg/detail", method = {RequestMethod.GET})
    public String conversationDetail(ModelMap map, @Param("conversationId") String conversationId) {
            List<Message> conversationList = messageService.getConversationDetail(conversationId);
            List<ViewObject> messages = new ArrayList<>();
            for (Message msg : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("message", msg);
                User user = userService.selectById(String.valueOf(msg.getFromId()));
                if (user == null) {
                    continue;
                }
                vo.set("headUrl", user.getAvatar());
                vo.set("userId", user.getId());
                messages.add(vo);
            }
            map.put("messages", messages);
            return "";
    }


}
