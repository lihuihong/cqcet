package com.cqcet.controller.show;

import com.cqcet.async.EventModel;
import com.cqcet.async.EventProducer;
import com.cqcet.async.EventType;
import com.cqcet.async.handler.LikeHandler;
import com.cqcet.entity.Article;
import com.cqcet.entity.EntityType;
import com.cqcet.entity.Result;
import com.cqcet.entity.User;
import com.cqcet.services.ArticleService;
import com.cqcet.services.LikeService;
import com.cqcet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 点赞
 * Created by 那个谁 on 2018/10/28.
 */
@Controller
@RequestMapping(value = "/show")
public class LikeController {

    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    EventProducer eventProducer;

    @RequestMapping(value = "/like.json", method = {RequestMethod.POST})
    @ResponseBody
    public Result like(@RequestParam("acticleId") int acticleId,
                       HttpServletRequest request) {
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return Result.error("未登录");
        }
        Article article = articleService.selectById(String.valueOf(acticleId));
        //发送事件
        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(Integer.parseInt(userInfo.getId())).setEntityId(acticleId)
                .setEntityType(EntityType.ENTITY_COMMENT).setEntityOwnerId(article.getUserId())
                .setExt("articleId", String.valueOf(acticleId)));
        likeService.like(Integer.parseInt(userInfo.getId()), EntityType.ENTITY_ARTIUCLE, acticleId);
        return Result.success();
    }

    @RequestMapping(value ="/dislike.json", method = {RequestMethod.POST})
    @ResponseBody
    public Result dislike(@RequestParam("acticleId") int acticleId,
                       HttpServletRequest request) {
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return Result.error("未登录");
        }
        likeService.disLike(Integer.parseInt(userInfo.getId()), EntityType.ENTITY_ARTIUCLE, acticleId);
        return Result.success();
    }
}
