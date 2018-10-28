package com.cqcet.dao;

import com.cqcet.entity.Type;
import com.cqcet.entity.UserGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 那个谁on 2018/9/30.
 */
public interface UserGradeMapper {


    List<UserGrade> list();


    void insert(UserGrade userGrade);


    void update(UserGrade userGrade);


    void delete(@Param("idArr")String[] idArr);


    int countByName(@Param("name") String name, @Param("id") String id);


    int countByIntegral(@Param("integral") String integral, @Param("id") String id);
}
