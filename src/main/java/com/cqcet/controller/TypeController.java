package com.cqcet.controller;

import com.cqcet.entity.Result;
import com.cqcet.entity.Type;
import com.cqcet.entity.UserGrade;
import com.cqcet.exception.LException;
import com.cqcet.services.TypeService;
import com.cqcet.services.UserGradeService;
import com.sun.org.apache.bcel.internal.generic.IASTORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那个谁 on 2018/9/13.
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserGradeService userGradeService;

    /**
     * 查询所有帖子分类
     * @param map
     * @return
     */
    @RequestMapping("/list.action")
    public String list(ModelMap map){
        List<Type> list = typeService.list();
        map.put("list",list);
        return "type/list";
    }

    /**
     * 保存帖子分类
     * @param idArr
     * @param sortArr
     * @param nameArr
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public Result save(@RequestParam(value="idArr") String[] idArr,
                       @RequestParam(value="sortArr") String[] sortArr,
                       @RequestParam(value="nameArr") String[] nameArr) throws LException {

        List<Type> typeList = new ArrayList<Type>();
        for (int i = 0; i < idArr.length; i++) {
            Type type = new Type();
            type.setId(idArr[i]);
            type.setName(nameArr[i]);
            type.setSort(sortArr[i]);

            typeList.add(type);
        }
        typeService.save(typeList);

        return Result.success();
    }

    /**
     * 删除帖子分类
     * @param idArr
     * @return
     * @throws LException
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public Result delete(@RequestParam(value="idArr") String[] idArr) throws LException {
        typeService.delete(idArr);
        return Result.success();
    }

    /**
     * 查询所有会员组分类
     * @param map
     * @return
     */
    @RequestMapping("/userGrade.action")
    public String userGrade(ModelMap map){
        List<UserGrade> list = userGradeService.list();
        map.put("list",list);
        return "type/user_list";
    }

    /**
     * 保存用户组
     * @param idArr
     * @param integralArr
     * @param nameArr
     * @return
     */
    @RequestMapping("/user_gradeSave.json")
    @ResponseBody
    public Result userGradeSave(@RequestParam(value="idArr") String[] idArr,
                                @RequestParam(value="integralArr") String[] integralArr,
                                @RequestParam(value="nameArr") String[] nameArr) throws LException {

        List<UserGrade> userGradeList = new ArrayList<UserGrade>();
        for (int i = 0; i < idArr.length; i++) {
            UserGrade userGrade = new UserGrade();
            userGrade.setId(idArr[i]);
            userGrade.setName(nameArr[i]);
            userGrade.setIntegral(integralArr[i]);

            userGradeList.add(userGrade);
        }

        userGradeService.save(userGradeList);

        return Result.success();
    }


    /**
     * 删除用户组
     * @param idArr
     * @return
     * @throws LException
     */
    @RequestMapping("/userGradeDelete.json")
    @ResponseBody
    public Result userGradeDelete(@RequestParam(value="idArr") String[] idArr) throws LException {
        userGradeService.delete(idArr);
        return Result.success();
    }

}
