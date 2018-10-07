package com.cqcet.dao;

import com.cqcet.entity.Type;
import com.cqcet.entity.UserGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * �û���Ա��
 * Created by �Ǹ�˭ on 2018/9/30.
 */
public interface UserGradeMapper {

    /**
     * ��ѯ���л�Ա�����
     */
    List<UserGrade> list();

    /**
     * ����һ���µ�����
     * @param userGrade
     */
    void insert(UserGrade userGrade);

    /**
     * ����һ������
     * @param userGrade
     */
    void update(UserGrade userGrade);

    /**
     * ����ɾ����Ա�����
     * @param idArr ��������
     */
    void delete(@Param("idArr")String[] idArr);

    /**
     * �û������Ƿ��ظ�
     * @param name
     * @param id
     * @return
     */
    int countByName(@Param("name") String name, @Param("id") String id);

    /**
     * �жϸû����Ƿ��ظ�
     * @param integral
     * @param id
     * @return
     */
    int countByIntegral(@Param("integral") String integral, @Param("id") String id);
}
