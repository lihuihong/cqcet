package com.cqcet.dao;

import com.cqcet.entity.Professional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/2.
 */
public interface ProfessionalMapper {

    /**
     * 查询所有专业
     */
    List<Professional> list();

    /**
     * 插入专业
     * @param professional
     */
    void insert(Professional professional);

    /**
     *  更新专业
     * @param professional
     */
    void update(Professional professional);

    /**
     * 批量删除专业
     * @param idArr 主键数组
     */
    void delete(@Param("idArr") String[] idArr);

    /**
     * 专业是否重复
     * @param name 帖子分类
     * @param id 主键
     * @return
     */
    int countByName(@Param("name") String name, @Param("id") String id);

    /**
     * 专业排序是否重复
     * @param sort 排序
     * @param id 主键
     * @return
     */
    int countBySort(@Param("sort") String sort, @Param("id") String id);

    /**
     * 查询学院id是否在使用
     * @param collegeId
     * @return
     */
    int countByCollegeIdArr(String collegeId);

    //根据专业id查询学院id
    int selectByProfessionalId(String professionalId);

    /**
     * 专业名称查询专业id
     * @param professional
     * @return
     */
    int idByName(String professional);
}
