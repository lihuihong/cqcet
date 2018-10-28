package com.cqcet.async;

import java.util.List;

/**
 * 事件接口
 * Created by 那个谁 on 2018/10/28.
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
