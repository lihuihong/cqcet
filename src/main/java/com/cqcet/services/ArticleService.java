package com.cqcet.services;

import com.cqcet.dao.ArticleMapper;
import com.cqcet.dao.TypeMapper;
import com.cqcet.entity.Article;
import com.cqcet.entity.College;
import com.cqcet.entity.Forum;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.Gson;
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
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有帖子
     * @param param
     * @return
     */
    public List<Article> list(Map<String, Object> param){
        List<Article> list = articleMapper.list(param);
        return list;
    }

    /**
     * 根据学院id查询该学院下帖子
     * @return
     */
    public List<Article> articleByCollegeId(String collegeId){
        return articleMapper.articleByCollegeId(collegeId,"0");
    }


    /**
     * 查询单个帖子信息并更新浏览量
     * @param id
     * @return
     */
    public Article selectById(String id){
        Article article = articleMapper.selectById(id);
        if (article != null){
            //获取当前的阅读量
            int viewCount = article.getViewCount();
            //阅读量自增
            viewCount ++;
            articleMapper.updateViewCount(id,viewCount);
        }
        return article;
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
        if (StringUtils.isEmpty(article.getId()) || article.getUserId()==null || String.valueOf(article.getUserId()) == "null") {
            // 新增
            article.setStatus("0");
            article.setUpdateTime(currentTime);
            article.setViewCount(1);

            articleMapper.insert(article);
        } else {
            // 更新
            article.setUpdateTime(currentTime);

            articleMapper.update(article);
        }

    }

    /**
     * 论坛对象
     * @param college
     * @return
     */
    public Forum selectByCollege(College college){
        //该学院下帖子数量
        int countArticle = articleMapper.countByCollegeId(college.getId(), "0");
        Forum forum = new Forum();
        forum.setArticleCount(countArticle);
        forum.setCollegeAvatar(college.getAvatar());
        forum.setCollegeDes(college.getDes());
        forum.setCollegeName(college.getName());
        int typeCount = typeMapper.countByCollegeId(college.getId());
        forum.setTypeCount(typeCount);
        forum.setCollegeId(college.getId());
        return forum;
    }

    /**
     * 根据typeId查询帖子总数
     * @param typeId
     * @return
     */
    public int countByTypeId(String typeId,String userId,String status) {
        return articleMapper.countByTypeId(typeId,userId,status);
    }

    /**
     * 查询用户最近的一篇帖子
     * @param userId
     * @return
     */
    public Article selectArticleByUserIdNow(String userId) {
        return articleMapper.selectArticleByUserIdNow(userId);
    }


}
