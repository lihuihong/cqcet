package com.cqcet.dao;

import com.cqcet.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 校验用户
     * @param userName 登录名
     * @param passWord 登录密码
     * @return
     */
    User checkUser(@Param("userName") String userName, @Param("passWord") String passWord);

    /**
     * 用户注册
     * @param user
     */
    void regist(User user);

    /**
     * 查询指定用户组的数量
     * @param level
     * @return
     */
    int countByGradeId(String level);

    /**
     * 查询该专业id是否在使用
     * @param professionalId
     * @return
     */
    int countByProfessionalIdArr(String professionalId);

    /**
     * 查询全部用户
     * @param param
     * @return
     */
    List<User> list(Map<String, Object> param);

    /**
     * 根据用户id查询信息
     * @param id
     * @return
     */
    User selectById(String id);

    /**
     * 查询用户名以及学号是否存在
     * @param studentId
     * @param username
     * @return
     */
    int countUser(@Param("studentId") String studentId, @Param("username") String username);

    /**
     * 添加用户
     * @param user
     */
    void insert(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 批量更新用户状态
     * @param param
     */
    void batchUpdate(Map<String, Object> param);
}