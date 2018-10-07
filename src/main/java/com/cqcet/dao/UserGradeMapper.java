package com.cqcet.dao;

import com.cqcet.entity.Type;
import com.cqcet.entity.UserGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户会员组
 * Created by 那个谁 on 2018/9/30.
 */
public interface UserGradeMapper {

    /**
     * 查询所有会员组分类
     */
    List<UserGrade> list();

    /**
     * 插入一条新的数据
     * @param userGrade
     */
    void insert(UserGrade userGrade);

    /**
     * 更新一条数据
     * @param userGrade
     */
    void update(UserGrade userGrade);

    /**
     * 批量删除会员组分类
     * @param idArr 主键数组
     */
    void delete(@Param("idArr")String[] idArr);

    /**
     * 用户组名是否重复
     * @param name
     * @param id
     * @return
     */
    int countByName(@Param("name") String name, @Param("id") String id);

    /**
     * 判断该积分是否重复
     * @param integral
     * @param id
     * @return
     */
    int countByIntegral(@Param("integral") String integral, @Param("id") String id);
}
