package com.cqcet.dao;

import com.cqcet.entity.Professional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by �Ǹ�˭ on 2018/10/2.
 */
public interface ProfessionalMapper {

    /**
     * ��ѯ����רҵ
     */
    List<Professional> list();

    /**
     * ����רҵ
     * @param professional
     */
    void insert(Professional professional);

    /**
     *  ����רҵ
     * @param professional
     */
    void update(Professional professional);

    /**
     * ����ɾ��רҵ
     * @param idArr ��������
     */
    void delete(@Param("idArr") String[] idArr);

    /**
     * רҵ�Ƿ��ظ�
     * @param name ���ӷ���
     * @param id ����
     * @return
     */
    int countByName(@Param("name") String name, @Param("id") String id);

    /**
     * רҵ�����Ƿ��ظ�
     * @param sort ����
     * @param id ����
     * @return
     */
    int countBySort(@Param("sort") String sort, @Param("id") String id);

    /**
     * ��ѯѧԺid�Ƿ���ʹ��
     * @param collegeId
     * @return
     */
    int countByCollegeIdArr(String collegeId);

    //����רҵid��ѯѧԺid
    int selectByProfessionalId(String professionalId);

    /**
     * רҵ���Ʋ�ѯרҵid
     * @param professional
     * @return
     */
    int idByName(String professional);
}
