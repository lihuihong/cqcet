package com.cqcet.controller;

import com.cqcet.entity.Article;
import com.cqcet.entity.Result;
import com.cqcet.services.ArticleService;
import com.cqcet.services.TypeService;
import com.cqcet.services.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/9/11.
 * 首页
 */
@Controller
@RequestMapping(value = "/show/index",method={RequestMethod.GET})
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * 主页
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/index.action")
    public String index(ModelMap map,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {

        return "show/index";
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
