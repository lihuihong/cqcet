package com.cqcet.services;

import com.cqcet.dao.ReviewMapper;
import com.cqcet.entity.Review;
import com.cqcet.entity.ReviewExample;
import com.cqcet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    public void add(Review c) {
        reviewMapper.insert(c);
    }

    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    public void update(Review c) {
        reviewMapper.updateByPrimaryKeySelective(c);
    }

    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    public List<Review> list(int pid){
        ReviewExample example =new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");

        List<Review> result =reviewMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(List<Review> reviews){
        for (Review review : reviews) {
            setUser(review);
        }
    }

    private void setUser(Review review) {
        int uid = review.getUid();
        User user =userService.selectById(String.valueOf(uid));
        review.setUser(user);
    }

    public int getCount(int pid) {
        return list(pid).size();
    }

}
