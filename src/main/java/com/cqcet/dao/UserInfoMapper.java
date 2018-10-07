package com.cqcet.dao;

import com.cqcet.entity.UserInfo;

import java.util.Map;

/**
 * 用户个人信息
 * Created by 那个谁 on 2018/10/7.
 */
public interface UserInfoMapper {

    /**
     * 添加信息
     * @param userInfo
     * @return
     */
    int insert(UserInfo userInfo);

    /**
     * 更新信息
     * @param userInfo
     */
    void update(UserInfo userInfo);

    /**
     * 批量变换用户组
     * @param param
     */
    void batchUpdate(Map<String, Object> param);
}
