package com.cqcet.async;

/**
 * 事件类型
 * Created by 那个谁on 2018/10/28.
 */
public enum  EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3);

    private int value;
    EventType(int value) { this.value = value; }
    public int getValue() { return value; }
}
