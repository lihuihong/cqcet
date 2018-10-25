package com.cqcet.services;

import com.cqcet.dao.MessageMapper;
import com.cqcet.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/25.
 */
@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    SensitiveService sensitiveService;

    /**
     * 发消息
     * @param message
     * @return
     */
    public int addMessage(Message message) {
        message.setContent(sensitiveService.filter(message.getContent()));
        return messageMapper.addMessage(message);
    }

    /**
     * 会话详情
     * @param conversationId
     * @return
     */
    public List<Message> getConversationDetail(String conversationId) {
        return messageMapper.getConversationDetail(conversationId);
    }

    /**
     * 会话
     * @param userId
     * @return
     */
    public List<Message> getConversationList(int userId) {
        return messageMapper.getConversationList(userId);
    }

    /**
     * 未读消息
     * @param userId
     * @param conversationId
     * @return
     */
    public int getConvesationUnreadCount(int userId, String conversationId) {
        return messageMapper.getConvesationUnreadCount(userId, conversationId);
    }
}
