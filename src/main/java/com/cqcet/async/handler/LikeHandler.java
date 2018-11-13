package com.cqcet.async.handler;

import com.cqcet.async.EventHandler;
import com.cqcet.async.EventModel;
import com.cqcet.async.EventType;
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
 * 点赞
 * Created by 那个谁 on 2018/10/28.
 */
@Component
public class LikeHandler  implements EventHandler {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        String fromId  = "0";
        String toId  = String.valueOf(model.getEntityOwnerId());
        message.setFromId(fromId);
        message.setToId(toId);
        message.setHasRead("0");
        message.setCreatedDate(new Date());
        message.setConversationId(Integer.valueOf(fromId) < Integer.valueOf(toId) ? String.format("%s_%s", fromId, toId) : String.format("%s_%s", toId, fromId));
        User user = userService.selectById(String.valueOf(model.getActorId()));
        message.setContent("用户" + user.getUsername()
                + "赞了你的,<a style='font-size: 20px' href='http://www.heylhh.com/show/detail.action?id="+model.getExt("articleId")+"'"+">帖子</a>");

        messageService.addMessage(message);

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
