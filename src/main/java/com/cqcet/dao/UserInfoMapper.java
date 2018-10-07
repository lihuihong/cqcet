package com.cqcet.dao;

import com.cqcet.entity.UserInfo;

import java.util.Map;

/**
 * �û�������Ϣ
 * Created by �Ǹ�˭ on 2018/10/7.
 */
public interface UserInfoMapper {

    /**
     * �����Ϣ
     * @param userInfo
     * @return
     */
    int insert(UserInfo userInfo);

    /**
     * ������Ϣ
     * @param userInfo
     */
    void update(UserInfo userInfo);

    /**
     * �����任�û���
     * @param param
     */
    void batchUpdate(Map<String, Object> param);
}
