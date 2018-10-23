package com.cqcet.services;

import com.cqcet.dao.AnswerMapper;
import com.cqcet.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zcq on 2018/10/16.
 */
@Service("AnswerService")
public class AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 添加回帖
     * @param answer
     * @return
     */
    public Integer save(Answer answer){
        return answerMapper.insert(answer);
    }

    /**
     * 修改回帖
     * @param answer
     * @return
     */
    public Integer update(Answer answer){
        return answerMapper.update(answer);
    }

    /**
     * 针对文章id查询所有回复
     * @param acticleId
     * @return
     */
    public List<Answer> queryAnswerById(Integer acticleId){
        return answerMapper.queryAnswerById(acticleId);
    }

    /**
     * 针对回复子id查询所有子回复
     * @param acticleId
     * @return
     */
    public List<Answer> queryAnswerByChildId(Integer acticleId,Integer childId){
        return answerMapper.queryAnswerByChildId(acticleId,childId);
    }

}
