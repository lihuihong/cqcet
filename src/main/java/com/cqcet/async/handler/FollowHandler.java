package com.cqcet.async.handler;

import com.cqcet.async.EventHandler;
import com.cqcet.async.EventModel;
import com.cqcet.async.EventType;
import com.cqcet.entity.EntityType;
import com.cqcet.entity.Message;
import com.cqcet.entity.User;
import com.cqcet.services.MessageService;
import com.cqcet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 关注
 * Created by 那个谁 on 2018/11/7.
 */
@Component
public class FollowHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        String fromId  = "0";
        message.setFromId(fromId);
        String toId  = String.valueOf(model.getEntityOwnerId());
        message.setToId(toId);
        message.setHasRead("0");
        message.setCreatedDate(new Date());
        message.setConversationId(Integer.valueOf(fromId) < Integer.valueOf(toId) ? String.format("%s_%s", fromId, toId) : String.format("%s_%s", toId, fromId));
        User user = userService.selectById(String.valueOf(model.getActorId()));
        if (model.getEntityType() == EntityType.ENTITY_USER) {
            message.setContent("用户" + "<a style='font-size: 20px' href='http://www.heylhh.com/show/personArticle.action?id?id="+model.getExt("articleId")+"'"+">"+user.getUsername()+"</a>"
                    + "关注了你。");
        }

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.FOLLOW);
    }
}
