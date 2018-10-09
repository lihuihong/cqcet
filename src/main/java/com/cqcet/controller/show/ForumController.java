package com.cqcet.controller.show;

import com.cqcet.entity.Article;
import com.cqcet.entity.College;
import com.cqcet.entity.Forum;
import com.cqcet.entity.Result;
import com.cqcet.services.ArticleService;
import com.cqcet.services.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 论坛 on 2018/9/11.
 * 帖子
 */
@Controller
@RequestMapping(value = "/show",method={RequestMethod.GET})
public class ForumController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CollegeService collegeService;


    /**
     * 查询所有帖子
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
        map.put("list",forums);
        return "show/forum";
    }

    /**
     * 根据根据主键查询帖子详情
     * @return
     */
    @RequestMapping("/answer.action")
    public String detail(ModelMap map,@RequestParam(value = "collegeId") String collegeId) {
        return "show/answer";
    }

    /**
     * 根据帖子分类来查询帖子
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/detail.action")
    public String type(ModelMap map,
                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "4")int pageSize){

        return "show/detail";
    }

    /**
     * 根据关键字搜索帖子
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/app.action")
    public String search(ModelMap map,
                         @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "4")int pageSize){

        return "show/app";
    }

    /**
     * 帖子保存
     * @param article
     * @return
     */
    @RequestMapping("/save.json")
    public Result save(Article article){

        articleService.save(article);

        return Result.success();
    }

    public void edit(){

    }
}
