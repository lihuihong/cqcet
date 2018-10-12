package com.cqcet.dao;

import com.cqcet.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {
    /**
     * 查询所有帖子分类
     */
    List<Type> list();

    /**
     * 插入一条新的数据
     * @param type
     */
    void insert(Type type);

    /**
     *  更新一条数据
     * @param type
     */
    void update(Type type);

    /**
     * 批量删除帖子分类
     * @param idArr 主键数组
     */
    void delete(@Param("idArr")String[] idArr);

    /**
     * 查询帖子分类的数量，用于校验帖子分类名字重复
     * @param name 帖子分类
     * @param id 主键
     * @return
     */
    int countByName(@Param("name") String name, @Param("id") String id);

    /**
     * 查询帖子分类排序的数量，用于校验帖子分类排序重复
     * @param sort 排序
     * @param id 主键
     * @return
     */
    int countBySort(@Param("sort") String sort, @Param("id") String id);

    /**
     * 根据学院id查询该学院下文章的数量
     * @param collegeId
     * @return
     */
    int countByCollegeId(@Param("collegeId") String collegeId);

    /**
     * 根据帖子名称获取帖子id以及学院id
     * @param articleName
     * @return
     */
    Type selectByName(String articleName);
}