package com.cqcet.dao;

import com.cqcet.entity.UserInfo;

import java.util.Map;

/**
 * Created by 那个谁 on 2018/10/7.
 */
public interface UserInfoMapper {


    int insert(UserInfo userInfo);


    void update(UserInfo userInfo);


    void batchUpdate(Map<String, Object> param);
}
