package com.cqcet.dao;

import com.cqcet.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    /**
     * 单个帖子信息
     * @param id
     * @return
     */
    Article selectById(String id);

    /**
     * 查询所有帖子
     * @param param
     * @return
     */
    List<Article> list(Map<String, Object> param);

    /**
     * 浏览量自增
     * @param id 主键
     * @param viewCount 浏览量
     */
    void updateViewCount(@Param("id")String id, @Param("viewCount") int viewCount);

    /**
     * 新增帖子
     * @param article
     * @return
     */
    int insert(Article article);

    /**
     * 更新帖子
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 批量更新
     * @param param
     */
    void batchUpdate(Map<String,Object> param);

    /**
     * 根据文章主键批量删除文章
     *
     * @param idArr
     */
    void batchDelete(@Param("idArr") String[] idArr);

    /**
     * 根据文章分类，查询文章的数量
     *
     * @param typeIdArr
     *            分类id数组
     * @param status
     *            文章的状态
     * @return
     */
    int countByTypeIdArr(@Param("typeIdArr") String[] typeIdArr, @Param("status") String status);


    /**
     * 根据学院id查询文章数量
     *
     * @param collegeId
     *            分类id数组
     * @param status
     *            文章的状态
     * @return
     */
    int countByCollegeId(@Param("collegeId") String collegeId, @Param("status") String status);

    /**
     * 根据文章分类批量删除回收站的文章
     *
     * @param typeIdArr
     *            分类id数组
     */
    void batchDeleteByTypeIdArr(@Param("typeIdArr") String[] typeIdArr);


    /**
     * 根据学院id查询该学院下帖子
     * @param collegeId
     * @param status
     * @return
     */
    List<Article> articleByCollegeId(@Param("collegeId") String collegeId, @Param("status") String status);





}