package com.cqcet.services;

import com.cqcet.dao.ProfessionalMapper;
import com.cqcet.dao.UserMapper;
import com.cqcet.entity.Professional;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 专业管理
 * Created by 那个谁on 2018/10/2.
 */
@Service("ProfessionalService")
public class ProfessionalService {

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Professional> list() {
        return professionalMapper.list();
    }

    public void save(List<Professional> professionalList) throws LException {

        for (Professional professional : professionalList) {

            if (StringUtils.isEmpty(professional.getId())){
                //更新
                int count = professionalMapper.countByName(professional.getName(),null);
                if (count>0){
                    throw new LException("该专业已存在");
                }
                count = professionalMapper.countBySort(professional.getSort(),null);
                if (count>0){
                    throw new LException("该优先级已存在");
                }
                professionalMapper.insert(professional);
            }else {
                //更新
                int count = professionalMapper.countByName(professional.getName(),professional.getId());
                if (count>0){
                    throw new LException("该专业已存在");
                }
                count = professionalMapper.countBySort(professional.getSort(),professional.getId());
                if (count>0){
                    throw new LException("该优先级已存在");
                }
                professionalMapper.update(professional);
            }
        }

    }

    /**
     * 删除专业
     *
     * @param idArr
     * @throws LException
     */
    public void delete(String[] idArr) throws LException {
        // 判断该学院id有没有被使用
        for (int i = 0; i < idArr.length; i++) {
            int nCount = userMapper.countByProfessionalIdArr(idArr[i]);
            if (nCount > 0) {
                // 被占用了，禁止删除
                throw new LException("该专业下存在用户!!!");
            }
        }


        // 删除该分类
        professionalMapper.delete(idArr);
    }
}
