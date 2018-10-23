package com.cqcet.dao;

import com.cqcet.entity.Answer;

import java.util.List;

public interface AnswerMapper {

    /**
     * 新增回复
     * @param answer
     * @return
     */
    Integer insert(Answer answer);


    /**
     * 修改回复
     * @param answer
     * @return
     */
    Integer update(Answer answer);


    /**
     * 针对指定id查询全部回复
     * @param acticleId
     * @return
     */
    List<Answer> queryAnswerById(Integer acticleId);



    /**
     * 查询所有子回复
     * @param childId
     * @return
     */
    List<Answer> queryAnswerByChildId(Integer acticleId, Integer childId);
}
