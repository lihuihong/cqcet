package com.cqcet.dao;

import com.cqcet.entity.Review;
import com.cqcet.entity.ReviewExample;

import java.util.List;

/**
 * 评价
 * Created by 那个谁 on 2018/11/15.
 */
public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    List<Review> selectByExample(ReviewExample example);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}
