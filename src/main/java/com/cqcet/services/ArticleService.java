package com.cqcet.services;

import com.cqcet.dao.ArticleMapper;
import com.cqcet.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/9/11.
 */
@Service("ArticleService")
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询所有帖子
     * @param param
     * @return
     */
    public List<Article> list(Map<String, Object> param){
        return articleMapper.list(param);
    }

    /**
     * 查询单个帖子信息并更新浏览量
     * @param id
     * @return
     */
    public Article selectById(String id){
        Article acticle = articleMapper.selectById(id);
        if (acticle != null){
            //获取当前的阅读量
            int viewCount = acticle.getViewCount();
            //阅读量自增
            viewCount ++;
            articleMapper.updateViewCount(id,viewCount);
        }
        return acticle;
    }

    /**
     * 批量更新
     * @param param
     */
    public void bacthUpdate(Map<String,Object> param){
        articleMapper.batchUpdate(param);
    }

    /**
     * 批量删除帖子
     * @param idArr
     */
    public void batchDelete(String[] idArr) {
        articleMapper.batchDelete(idArr);
    }

    /**
     * 添加帖子
     * @param article
     */
    public void save(Article article) {
        Date currentTime = new Date();
        // 判断是新增还是更新
        if (StringUtils.isEmpty(article.getId())) {
            // 新增
            article.setStatus(0);
            article.setUpdateTime(currentTime);
            article.setViewCount(1);

            articleMapper.insert(article);
        } else {
            // 更新
            article.setUpdateTime(currentTime);

            articleMapper.update(article);
        }

    }

}
