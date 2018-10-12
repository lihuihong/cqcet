package com.cqcet.services;

import com.cqcet.dao.ArticleMapper;
import com.cqcet.dao.TypeMapper;
import com.cqcet.entity.Type;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 那个谁 on 2018/9/11.
 */
@Service("TypeService")
public class TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @return
     */
    public List<Type> list() {
        return typeMapper.list();
    }

    /**
     * 批量更新/插入
     */
    public void save(List<Type> typeList) throws LException {

        for (Type type : typeList) {
            // 判断是需要插入还是需要更新
            if (StringUtils.isEmpty(type.getId())) {
                //判断帖子分类名是否重复
                int count = typeMapper.countByName(type.getName(), null);
                if (count > 0) {
                    throw new LException("该帖子分类已存在");
                }
                //判断排序是否重复
                count = typeMapper.countBySort(type.getSort(), null);
                if (count > 0) {
                    throw new LException("优先级该已存在");
                }
                // 插入
                typeMapper.insert(type);
            } else {
                // 更新
                //判断帖子分类名是否重复
                int count = typeMapper.countByName(type.getName(), type.getId());
                if (count > 0) {
                    throw new LException("该帖子分类已存在");
                }
                //判断排序是否重复
                count = typeMapper.countBySort(type.getSort(), type.getId());
                if (count > 0) {
                    throw new LException("优先级该已存在");
                }
                // 更新
                typeMapper.update(type);
            }
        }
    }

    /**
     * 批量删除文章分类
     *
     * @param idArr
     * @throws LException
     */
    public void delete(String[] idArr) throws LException {
        // 判断该分类id有没有被使用
        int nCount = articleMapper.countByTypeIdArr(idArr, "0");
        if (nCount > 0) {
            // 被占用了，禁止删除
            throw new LException("already exists and cannot be deleted!!!");
        }

        // 先删除该分类下所有回收站的文章
        articleMapper.batchDeleteByTypeIdArr(idArr);
        // 删除该分类
        typeMapper.delete(idArr);
    }

    /**
     * 根据帖子名称获取帖子id以及学院id
     * @param articleName
     * @return
     */
    public Type selectByName(String articleName){
        return typeMapper.selectByName(articleName);
    }
}
