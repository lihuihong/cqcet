package com.cqcet.dao;

import com.cqcet.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * վ����
 * Created by �Ǹ�˭ on 2018/10/25.
 */
public interface MessageMapper {


    int addMessage(Message message);


    List<Message> getConversationDetail(@Param("conversationId") String conversationId);

    int getConvesationUnreadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);

    List<Message> getConversationList(@Param("userId") int userId);
}
