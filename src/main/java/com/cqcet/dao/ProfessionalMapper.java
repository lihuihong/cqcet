package com.cqcet.dao;

import com.cqcet.entity.Professional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/2.
 */
public interface ProfessionalMapper {


    List<Professional> list();


    void insert(Professional professional);


    void update(Professional professional);


    void delete(@Param("idArr") String[] idArr);


    int countByName(@Param("name") String name, @Param("id") String id);


    int countBySort(@Param("sort") String sort, @Param("id") String id);


    int countByCollegeIdArr(String collegeId);

    int selectByProfessionalId(String professionalId);


    int idByName(String professional);
}
