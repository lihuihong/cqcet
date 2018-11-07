package com.cqcet.controller;

import com.cqcet.entity.Answer;
import com.cqcet.entity.Article;
import com.cqcet.entity.Result;
import com.cqcet.entity.User;
import com.cqcet.exception.LException;
import com.cqcet.services.AnswerService;
import com.cqcet.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Created by zcq on 2018/10/16.
 * 回帖
 */
@Controller
@RequestMapping(value = "/show",method={RequestMethod.GET})
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;


    /**
     * 帖子保存
     * @returntring
     */
    @RequestMapping(value = "/saveAnswer.json" , method = {RequestMethod.POST})
    @ResponseBody
    public Result save(HttpServletRequest request, @RequestParam(value = "articleId") int articleId,
                       @RequestParam(value = "content") String content,
                       @RequestParam(value = "answerId") String answerId,
                       @RequestParam(value = "childId") String childId) throws LException {

        //得到当前用户登录的id
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            throw new LException("未登录");
        }

        Answer answer = new Answer();
        answer.setActicleId(articleId);
        answer.setContent(content);
        answer.setUserId(Integer.parseInt(userInfo.getId()));

        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(time);
        answer.setUpdateTime(time);

        if(answer==null || answerId.equals("")){
            answerService.save(answer);
        }else{
            if(childId==null || childId.equals("")){
                answer.setId(Integer.parseInt(answerId));
                answer.setChildId(Integer.parseInt(answerId));
                answerService.update(answer);
                answer.setChildId(null);
                answer.setId(null);
                answer.setParentId(Integer.parseInt(answerId));
                answerService.save(answer);
            }else{
                answer.setParentId(Integer.parseInt(childId));
                answerService.save(answer);
            }
        }

        return Result.success();
    }


    /**
     * 提交评论
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post_ans.json",method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request) throws LException {

        //得到当前用户登录的id
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            throw new LException("未登录");
        }

        //Map<String, Object> info = userService.selectUser(request);
        return Result.success();
    }


    public void edit(){

    }
}
