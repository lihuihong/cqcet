package com.cqcet.controller.show;

import com.cqcet.async.EventModel;
import com.cqcet.async.EventProducer;
import com.cqcet.async.EventType;
import com.cqcet.entity.EntityType;
import com.cqcet.entity.Result;
import com.cqcet.entity.User;
import com.cqcet.services.FollowService;
import com.cqcet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关注
 * Created by 那个谁 on 2018/11/7.
 */
@Controller
@RequestMapping(value = "/show")
public class FollowController {

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @Autowired
    EventProducer eventProducer;

    /**
     * 关注用户
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/followUser.json", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result followUser(@RequestParam("userId") int userId,
                             HttpServletRequest request) {
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return Result.error("未登录");
        }

        boolean ret = followService.follow(Integer.parseInt(userInfo.getId()), EntityType.ENTITY_USER, userId);

        eventProducer.fireEvent(new EventModel(EventType.FOLLOW)
                .setActorId(Integer.parseInt(userInfo.getId())).setEntityId(userId)
                .setExt("articleId", String.valueOf(userInfo.getId()))
                .setEntityType(EntityType.ENTITY_USER).setEntityOwnerId(userId));

        // 返回关注的人数
        return Result.success(ret ? 0 : 1, String.valueOf(followService.getFolloweeCount(Integer.parseInt(userInfo.getId()), EntityType.ENTITY_USER)));
    }

    /**
     * 取消关注
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/unfollowUser.json",method = {RequestMethod.POST})
    @ResponseBody
    public Result unfollowUser(@RequestParam("userId") int userId,
                               HttpServletRequest request) {
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return Result.error("未登录");
        }

        boolean ret = followService.unfollow(Integer.parseInt(userInfo.getId()), EntityType.ENTITY_USER, userId);

        eventProducer.fireEvent(new EventModel(EventType.UNFOLLOW)
                .setActorId(Integer.parseInt(userInfo.getId())).setEntityId(userId)
                .setExt("articleId", String.valueOf(userId))
                .setEntityType(EntityType.ENTITY_USER).setEntityOwnerId(userId));

        // 返回关注的人数
        return Result.success(ret ? 0 : 1, String.valueOf(followService.getFolloweeCount(Integer.parseInt(userInfo.getId()), EntityType.ENTITY_USER)));
    }

    /**
     * 关注
     * 粉丝列表
     * @param map
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/followees.action", method = {RequestMethod.GET})
    public String followees(ModelMap map, @RequestParam("userId") int userId,
                               HttpServletRequest request) {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        map.put("user",userInfo);
        List<Integer> followeeIds = followService.getFollowees(userId, EntityType.ENTITY_USER, 0, 10);

        if (userInfo.getUsername() != null) {
            List<Map<String,Object>> usersInfo = getUsersInfo(Integer.parseInt(userInfo.getId()), followeeIds);
            map.put("followees", usersInfo);
        } else {
            map.put("followees", getUsersInfo(0, followeeIds));
        }
        map.put("followeeCount", followService.getFolloweeCount(userId, EntityType.ENTITY_USER));
        map.put("followeeCurUser", userService.selectById(String.valueOf(userId)));

        List<Integer> followerIds = followService.getFollowers(EntityType.ENTITY_USER, userId, 0, 10);
        if (userInfo.getUsername() != null) {
            map.put("followers", getUsersInfo(Integer.parseInt(userInfo.getId()), followerIds));
        } else {
            map.put("followers", getUsersInfo(0, followerIds));
        }
        map.put("followerCount", followService.getFollowerCount(EntityType.ENTITY_USER, userId));
        map.put("followerCurUser", userService.selectById(String.valueOf(userId)));

        return "/show/followees";
    }
    private List<Map<String,Object>> getUsersInfo(int localUserId, List<Integer> userIds) {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (Integer uid : userIds) {
            User user = userService.selectById(String.valueOf(uid));
            if (user == null) {
                continue;
            }
            Map<String,Object> map = new HashMap<>();
            map.put("user", user);
            map.put("followerCount", followService.getFollowerCount(EntityType.ENTITY_USER, uid));
            map.put("followeeCount", followService.getFolloweeCount(uid, EntityType.ENTITY_USER));
            if (localUserId != 0) {
                map.put("followed", followService.isFollower(localUserId, EntityType.ENTITY_USER, uid));
            } else {
                map.put("followed", false);
            }
            list.add(map);
        }
        return list;
    }
}
