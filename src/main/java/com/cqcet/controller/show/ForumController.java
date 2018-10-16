package com.cqcet.controller.show;

import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.ArticleService;
import com.cqcet.services.CollegeService;
import com.cqcet.services.TypeService;
import com.cqcet.services.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 论坛 on 2018/9/11.
 * 帖子
 */
@Controller
@RequestMapping(value = "/show")
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
    public String index(ModelMap map, HttpServletRequest request) throws LException {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }

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
        if (userInfo == null) {

            return "/show/login";
        }
        //得到当前用户登录的id
        String userId = userInfo.getId();
        Map<String, Object> param = new HashMap<>();
        if (!(userId.equals(null))) {
            User user = userService.selectById(userId);
            map.put("user", user);
            param.put("userId", userId);
            param.put("status", "0");
            param.put("collegeId", collegeId);
            list = articleService.list(param);
            map.put("userId", userId);
            map.put("articleList", list);
        } else {
            param.put("status", "0");
            param.put("collegeId", collegeId);
            list = articleService.list(param);
            map.put("articleList", list);
        }
        //学院信息
        College college = collegeService.listById(collegeId);
        //pageHelper分页插件
        //在查询之前调用，传入当前页码，以及每一页显示多少条数据
        PageMethod.startPage(pageNum, pageSize);
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
    public String type(ModelMap map, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "") String id,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) throws LException {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        //该学院下帖子信息详情
        Article article = articleService.selectById(id);
        User user = userService.selectById(String.valueOf(article.getUserId()));
        map.put("user", user);
        Map<String, Object> param = new HashMap<>();
        param.put("userId", article.getUserId());
        param.put("status", "0");
        map.put("article", article);
        map.put("articleList", articleService.list(param));

        return "show/detail";
    }

    /**
     * 根据用户id查询用户全部帖子
     *
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/personArticle.action")
    public String personArticle(ModelMap map, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "") String id,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", id);
        param.put("status", 0);

        List<Article> articles = articleService.list(param);
        List<List> allTypes = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (Article article : articles) {
            List<Object> types = new ArrayList();
            //根据所有帖子分类id查询所有帖子分类类型
            Type type = typeService.articleTypeByTypeId(String.valueOf(article.getTypeId()));
            //根据typeId查询帖子总数
            int count = articleService.countByTypeId(type.getId(), id, "0");
            //去掉重复的帖子分类名
            if (!(set.contains(type.getName()))) {
                types.add(type);
                types.add(count);
                allTypes.add(types);
                set.add(type.getName());
            }
        }
        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);

        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);
        User user = userService.selectById(id);
        map.put("user", user);
        //List<Type> types = typeService.list();

        // 查询所有帖子分类
        map.put("typeList", allTypes);

        return "show/personArticle";
    }

    /**
     * 根据帖子分类id查询用户帖子
     *
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/article_type.action")
    public String articleType(ModelMap map, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "") String id,
                              @RequestParam(value = "typeId", defaultValue = "") String typeId,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",id);
        param.put("status", 0);

        List<Article> articles = articleService.list(param);
        List<List> allTypes = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (Article article : articles) {
            List<Object> types = new ArrayList();
            //根据所有帖子分类id查询所有帖子分类类型
            Type type = typeService.articleTypeByTypeId(String.valueOf(article.getTypeId()));
            //根据typeId查询帖子总数
            int count = articleService.countByTypeId(type.getId(), id, "0");
            //去掉重复的帖子分类名
            if (!(set.contains(type.getName()))) {
                types.add(type);
                types.add(count);
                allTypes.add(types);
                set.add(type.getName());
            }
        }
        map.put("typeName", typeService.articleTypeByTypeId(typeId).getName());
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("typeId",typeId);
        param1.put("userId",id);
        param1.put("status", 0);
        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param1);

        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);
        User user = userService.selectById(id);
        map.put("user", user);
        //List<Type> types = typeService.list();
        // 查询所有帖子分类
        map.put("typeList", allTypes);
        return "show/type";
    }

    /**
     * 根据关键字搜索帖子
     *
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/search.action")
    public String search(ModelMap map,@RequestParam(value = "keyWord")String keyWord,
                         @RequestParam(required = false, value = "typeId") String typeId,
                         @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "8")int pageSize){

        Map<String, Object> param = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }
        if (!StringUtils.isEmpty(typeId)) {
            param.put("typeId", "%" + typeId.trim() + "%");
        }
        param.put("status", 0);

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);
        map.put("keyWord", keyWord);
        return "show/search";
    }

    /**
     * 应用中心
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/app.action")
    public String app(ModelMap map,
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
    public String posted(ModelMap map,
                         HttpServletRequest request,
                         @RequestParam(required = false, value = "id") String id) throws LException {

        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            return "/show/login";
        }
        // 单个文章的信息
        if (!StringUtils.isEmpty(id)) {
            Article article = articleService.selectById(id);
            map.put("article", article);
        }
        map.put("typeList", typeService.list());
        return "show/posted";
    }

    /**
     * 帖子保存
     *
     * @return
     */
    @RequestMapping(value = "/save_article.json", method = {RequestMethod.POST})
    @ResponseBody
    public Result save(HttpServletRequest request, @RequestParam(value = "stem") String stem,
                       @RequestParam(value = "title") String title,
                       @RequestParam(value = "type") String type,
                       @RequestParam(value = "id") String id) throws LException {
        //得到当前用户登录的id
        User userInfo = userService.getUserInfo(request);
        if (userInfo == null) {
            throw new LException("未登录");
        }
        // 校验标题长度
        if (title.length()>80) {
            throw new LException("标题超过了80个");
        }
        Article article = new Article();
        article.setContent(stem.replaceAll("(\r\n|\n)", "<br/>"));
        article.setTitle(title);
        article.setUserId(Integer.valueOf(userInfo.getId()));
        //判断帖子id
        if (id != ""){
            article.setId(Integer.valueOf(id));
        }
        Type typeName = typeService.articleTypeByTypeId(type);
        article.setCollegeId(userInfo.getCollege());
        article.setTypeId(Integer.valueOf(typeName.getId()));
        articleService.save(article);
        return Result.success();
    }

    /**
     * 删除帖子
     *
     * @return
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public Result delete(
            @RequestParam(value = "articleId") String[] articleId) {

        articleService.batchDelete(articleId);

        return Result.success();
    }

}
