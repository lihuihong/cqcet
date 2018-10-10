package com.cqcet.services;

import com.cqcet.dao.ArticleMapper;
import com.cqcet.dao.CollegeMapper;
import com.cqcet.dao.ProfessionalMapper;
import com.cqcet.entity.College;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 学院管理
 * Created by 那个谁 on 2018/9/11.
 */
@Service("CollegeService")
public class CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private ProfessionalMapper professionalMapper;

    /**
     * @return
     */
    public List<College> list() {
        return collegeMapper.list();
    }

    /**
     * 根据学院id查询学院信息
     * @return
     */
    public College listById(String id){
        return collegeMapper.listById(id);
    }

    /**
     * 更新/插入学院
     */
    public void save(List<College> collegeList) throws LException {

        for (College college : collegeList) {
            // 判断是需要插入还是需要更新
            if (StringUtils.isEmpty(college.getId())) {
                //判断学院是否重复
                int count = collegeMapper.countByName(college.getName(), null);
                if (count > 0) {
                    throw new LException("该学院已存在");
                }
                //判断排序是否重复
                count = collegeMapper.countBySort(college.getSort(), null);
                if (count > 0) {
                    throw new LException("该优先级已存在");
                }
                // 插入
                collegeMapper.insert(college);
            } else {
                // 更新
                //判断学院是否重复
                int count = collegeMapper.countByName(college.getName(), college.getId());
                if (count > 0) {
                    throw new LException("该学院已存在");
                }
                //判断排序是否重复
                count = collegeMapper.countBySort(college.getSort(), college.getId());
                if (count > 0) {
                    throw new LException("该优先级已存在");
                }
                // 更新
                collegeMapper.update(college);
            }
        }
    }

    /**
     * 删除学院
     *
     * @param idArr
     * @throws LException
     */
    public void delete(String[] idArr) throws LException {
        // 判断该学院id有没有被使用
        for (int i = 0; i < idArr.length; i++) {
            int nCount = professionalMapper.countByCollegeIdArr(idArr[i]);
            if (nCount > 0) {
                // 被占用了，禁止删除
                throw new LException("already exists and cannot be deleted!!!");
            }
        }


        // 删除该分类
        collegeMapper.delete(idArr);
    }
}
