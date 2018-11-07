package com.cqcet.controller.show;

import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.MessageService;
import com.cqcet.services.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
     *
     * @param map
     * @param request
     * @param toName
     * @param content
     * @return
     * @throws LException
     */
    @RequestMapping(value = "/msg/addMessage.json", method = {RequestMethod.POST})
    @ResponseBody
    public Result addMessage(ModelMap map, HttpServletRequest request,
                             @RequestParam("toName") String toName,
                             @RequestParam("toContent") String content) throws LException {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return Result.error("未登录");
        }
        User user = userService.selectUser(toName, null);
        if (user == null) {
            throw new LException("用户不存在");
        }
        Message msg = new Message();
        msg.setContent(content);
        String fromId = userInfo.getId();
        msg.setFromId(fromId);
        String toId = user.getId();
        msg.setToId(toId);
        msg.setHasRead("0");
        msg.setCreatedDate(new Date());
        msg.setConversationId(Integer.valueOf(fromId) < Integer.valueOf(toId) ? String.format("%s_%s", fromId, toId) : String.format("%s_%s", toId, fromId));
        messageService.addMessage(msg);
        return Result.success();
    }

    /**
     * 消息列表
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/msg/list.action", method = {RequestMethod.GET})
    public String conversationDetail(ModelMap map, HttpServletRequest request,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        String localUserId = userInfo.getId();
        List<Map<String, Object>> conversations = new ArrayList<>();
        List<Message> conversationList = messageService.getConversationList(Integer.parseInt(localUserId));
        int unreadCount = 0;
        for (Message msg : conversationList) {
            Map<String, Object> param = new HashMap<>();
            param.put("conversation", msg);
            //System.out.println(msg.getCreatedDate()+",,,,,,"+new Date());
            String targetId = String.valueOf(msg.getFromId().equals(localUserId) ? msg.getToId() : msg.getFromId());
            User user = userService.selectById(targetId);
            param.put("user", user);
            //未读消息
            int count = messageService.getConvesationUnreadCount(Integer.parseInt(msg.getToId()), msg.getConversationId());
            unreadCount = unreadCount + count;
            param.put("unread", count);
            conversations.add(param);

        }
        PageMethod.startPage(pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(conversations);
        map.put("conversations", pageInfo);
        User user = userService.selectById(userInfo.getId());
        map.put("user", user);
        //未读消息总数
        map.put("unreadCount",unreadCount);
        return "show/letter";
    }

    /**
     * 消息详情
     *
     * @param map
     * @param conversationId
     * @return
     */
    @RequestMapping(value = "/msg/detail.action", method = {RequestMethod.GET})
    public String conversationDetail(ModelMap map, HttpServletRequest request,
                                     @Param("conversationId") String conversationId,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        List<Message> conversationList = messageService.getConversationDetail(conversationId);
        List<Map<String, Object>> messages = new ArrayList<>();
        for (Message msg : conversationList) {
            Map<String, Object> param = new HashMap<>();
            param.put("message", msg);
            User user = userService.selectById(String.valueOf(msg.getToId()));
            param.put("user", user);
            param.put("headUrl", user.getAvatar());
            param.put("userId", user.getId());
            messages.add(param);
        }
        PageMethod.startPage(pageNum, pageSize);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(messages);
        User user = userService.selectById(userInfo.getId());
        map.put("user", user);
        map.put("messages", pageInfo);
        return "show/letterDetail";
    }


}
