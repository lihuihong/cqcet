package com.cqcet.controller;

import com.cqcet.entity.Article;
import com.cqcet.entity.Result;
import com.cqcet.services.ArticleService;
import com.cqcet.services.TypeService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * 帖子
 */
@Controller
@RequestMapping(value = "/article",method={RequestMethod.GET})
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TypeService typeService;

    /**
     * 查询所有帖子
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/index.action")
    public String index(ModelMap map,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {

        Map<String,Object> param = new HashMap<String, Object>();

        param.put("status",0);

        //pageHelper分页插件
        //在查询之前调用，传入当前页码，以及每一页显示多少条数据
        PageMethod.startPage(pageNum,pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo =new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);

        // 查询所有文章分类
        map.put("typeList", typeService.list());

        return "portal/index";
    }

    /**
     * 根据根据主键查询帖子详情
     * @return
     */
    @RequestMapping("/detail.action")
    public String detail(ModelMap map, @RequestParam(value = "id") String id) {

        Article article = articleService.selectById(id);
        if (article == null) {
            return "/404";
        }
        map.put("articleInfo", article);
        return "protal/type";
    }

    /**
     * 根据帖子分类来查询帖子
     * @param map
     * @param typeId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/type.action")
    public String type(ModelMap map, @RequestParam(value = "typeId") String typeId,
                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "4")int pageSize){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("typeId",typeId);
        param.put("status",0);

        //pageHelper分页插件
        //在查询之前调用，传入当前页码，以及每一页显示多少条数据
        PageMethod.startPage(pageNum,pageSize);
        List<Article> list = articleService.list(param);
        if (list.size()==0){
            return "/404";
        }
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo",pageInfo);
        return "list";
    }

    /**
     * 根据关键字搜索帖子
     * @param map
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/search.action")
    public String search(ModelMap map,@RequestParam(value = "keyWord")String keyWord,
                         @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "4")int pageSize){

        Map<String, Object> param = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }
        param.put("status", 0);

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);
        map.put("keyWord", keyWord);
        return "search";
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

    /**
     * 查询最近发布得帖子
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/new_article.json",method = RequestMethod.GET)
    public Result new_article(HttpServletRequest request){

        Article article = articleService.selectArticleByUserIdNow((String) (request.getSession().getAttribute("user")));
        //request.getSession().setAttribute("new_article",article);
        String  json = new Gson().toJson(article);
        return new Result().success().add("article_id",article.getId()).add("title",article.getTitle());
    }
}
