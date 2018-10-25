package com.cqcet.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ÄÇ¸öË­ on 2018/10/25.
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();
    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
