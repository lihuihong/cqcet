package com.cqcet.dao;

import com.cqcet.entity.College;
import com.cqcet.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollegeMapper {
    /**
     * 查询所有学院
     */
    List<College> list();

    /**
     * 插入新学院
     * @param college
     */
    void insert(College college);

    /**
     *  更新学院信息
     * @param college
     */
    void update(College college);

    /**
     * 批量删除学院
     * @param idArr 主键数组
     */
    void delete(@Param("idArr") String[] idArr);

    /**
     * 学院是否重复
     * @param name 学院名称
     * @param id 主键
     * @return
     */
    int countByName(@Param("name") String name, @Param("id") String id);

    /**
     * 学院名称查询学院id
     * @param name 学院名称
     * @return
     */
    int idByName(String name);


    /**
     * 学院排序是否重复
     * @param sort 排序
     * @param id 主键
     * @return
     */
    int countBySort(@Param("sort") String sort, @Param("id") String id);

    /**
     * 根据学院id查询学院信息
     * @param collegeId
     * @return
     */
    College listById(@Param("collegeId") String collegeId);


}