package com.cqcet.services;

import com.cqcet.dao.MessageMapper;
import com.cqcet.entity.Message;
import com.cqcet.util.RedisKeyUtil;
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
    @Autowired
    JedisAdapter jedisAdapter;

    /**
     * 发布私信
     * @param message
     * @return
     */
    public int addMessage(Message message) {
        message.setContent(sensitiveService.filter(message.getContent()));
        return messageMapper.addMessage(message);
    }

    /**
     * 私信详情
     * @param conversationId
     * @return
     */
    public List<Message> getConversationDetail(String conversationId) {
        return messageMapper.getConversationDetail(conversationId);
    }

    /**
     * 列表
     * @param userId
     * @return
     */
    public List<Message> getConversationList(int userId) {
        return messageMapper.getConversationList(userId);
    }

    /**
     * 私信总数
     * @param userId
     * @param conversationId
     * @return
     */
    public int getConvesationUnreadCount(int userId, String conversationId) {
        return messageMapper.getConvesationUnreadCount(userId, conversationId);
    }

    public long message(int userId, int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getMessage(entityType, userId);
        jedisAdapter.sadd(likeKey, String.valueOf(entityId));

        return jedisAdapter.scard(likeKey);
    }
}
