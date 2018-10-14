package com.cqcet.controller.show;

import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.ArticleService;
import com.cqcet.services.CollegeService;
import com.cqcet.services.TypeService;
import com.cqcet.services.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 论坛 on 2018/9/11.
 * 帖子
 */
@Controller
@RequestMapping(value = "/show", method = {RequestMethod.GET})
public class ForumController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;


    /**
     * 查询所有帖子
     *
     * @param map
     * @return
     */
    @RequestMapping("/forum.action")
    public String index(ModelMap map) {
        List<College> list = collegeService.list();
        List<Forum> forums = new ArrayList<Forum>();
        for (College college : list) {
            Forum forum = articleService.selectByCollege(college);
            forums.add(forum);
        }

        map.put("list", forums);
        return "show/forum";
    }

    /**
     * 根据根据学院id查询信息
     *
     * @return
     */
    @RequestMapping(value = "/answer.action", method = {RequestMethod.GET})
    public String answer(ModelMap map, HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                         @RequestParam(value = "collegeId") String collegeId) throws LException {

        List<Article> list;
        User userInfo = userService.getUserInfo(request);
        if (userInfo==null) {

            throw new LException("未登录");
        }
        //得到当前用户登录的id
        String userId = userInfo.getId();
        Map<String,Object> param = new HashMap<>();
        if (!(userId.equals("null"))){
            User user = userService.selectById(userId);
            map.put("user",user);
            param.put("userId",userId);
            param.put("status","0");
            param.put("collegeId",collegeId);
            list = articleService.list(param);
            map.put("userId",userId);
            map.put("articleList",list);
        }else {
            param.put("status","0");
            param.put("collegeId",collegeId);
            list = articleService.list(param);
            map.put("articleList",list);
        }
        //学院信息
        College college = collegeService.listById(collegeId);
        //pageHelper分页插件
        //在查询之前调用，传入当前页码，以及每一页显示多少条数据
        PageMethod.startPage(pageNum,pageSize);
        //该学院下帖子信息
        List<Article> articles = articleService.articleByCollegeId(collegeId);
        PageInfo<Article> articlePageInfo = new PageInfo<Article>(articles);
        map.put("college", college);
        map.put("articles", articlePageInfo);
        return "show/answer";
    }

    /**
     * 根据帖子id查询帖子详情
     *
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/detail.action")
    public String type(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {
        //该学院下帖子信息详情
        Article article = articleService.selectById(id);
        Map<String,Object> param = new HashMap<>();
        param.put("userId",article.getUserId());
        param.put("status","0");
        map.put("article", article);
        map.put("articleList",articleService.list(param));
        return "show/detail";
    }

    /**
     * 根据关键字搜索帖子
     *
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/app.action")
    public String search(ModelMap map,
                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {

        return "show/app";
    }

    /**
     * 发布帖子
     *
     * @param map
     * @return
     */
    @RequestMapping("/posted.action")
    public String posted(ModelMap map) {
        map.put("typeList",typeService.list());
        return "show/posted";
    }

    /**
     * 帖子保存
     *
     * @return
     */
    @RequestMapping("/save_article.json")
    public Result save(HttpServletRequest request,@RequestParam(value = "stem") String stem,
                       @RequestParam(value = "title") String title,
                       @RequestParam(value = "type") String type) {
        //得到当前用户登录的id
        String userId = String.valueOf(request.getSession().getAttribute("user"));
        Article article = new Article();
        article.setContent(stem);
        article.setTitle(title);
        article.setUserId(Integer.valueOf(userId));
        Type typeName = typeService.selectByName(type);
        article.setCollegeId(typeName.getCollegeId());
        article.setTypeId(Integer.valueOf(typeName.getId()));
        articleService.save(article);
        return Result.success();
    }

}
