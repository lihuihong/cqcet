package com.cqcet.controller;

import com.cqcet.entity.College;
import com.cqcet.entity.Professional;
import com.cqcet.entity.Result;
import com.cqcet.exception.LException;
import com.cqcet.services.CollegeService;
import com.cqcet.services.ProfessionalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 学院以及专业管理
 * Created by 那个谁 on 2018/10/2.
 */
@Controller
@RequestMapping("/collage")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private ProfessionalService professionalService;

    /**
     * 查询所有学院
     * @param map
     * @return
     */
    @RequestMapping("/college_list.action")
    public String college_list(ModelMap map){
        List<College> list = collegeService.list();
        map.put("list",list);
        return "/admin/college/list";
    }

    /**
     * 保存学院
     * @param idArr
     * @param sortArr
     * @param nameArr
     * @return
     * @throws LException
     */
    @RequestMapping("/college_save.json")
    @ResponseBody
    public Result college_save(@RequestParam(value="idArr") String[] idArr,
                               @RequestParam(value="sortArr") String[] sortArr,
                               @RequestParam(value="nameArr") String[] nameArr) throws LException{
        List<College> collegeList = new ArrayList<College>();
        for (int i = 0; i < idArr.length; i++) {
            College college = new College();
            college.setId(idArr[i]);
            college.setName(nameArr[i]);
            college.setSort(sortArr[i]);
            collegeList.add(college);
        }
        collegeService.save(collegeList);
        return Result.success();
    }

    /**
     * 删除学院
     * @param idArr
     * @return
     * @throws LException
     */
    @RequestMapping("/college_delete.json")
    @ResponseBody
    public Result college_delete(@RequestParam(value = "idArr") String[] idArr) throws LException{
        collegeService.delete(idArr);
        return Result.success();
    }


    /**
     * 查询所有专业
     * @param map
     * @return
     */
    @RequestMapping("/professional_list.action")
    public String professional_list(ModelMap map){
        List<Professional> list = professionalService.list();
        List<College> collegeList = collegeService.list();
        map.put("list",list);
        map.put("collegeList",collegeList);
        return "/admin/college/professional_list";
    }

    /**
     * 删除专业
     * @param idArr
     * @return
     * @throws LException
     */
    @RequestMapping("/professional_delete.json")
    @ResponseBody
    public Result professional_delete(@RequestParam(value = "idArr") String[] idArr) throws LException{
        professionalService.delete(idArr);
        return Result.success();
    }

    /**
     * 保存专业
     * @param idArr
     * @param sortArr
     * @param nameArr
     * @param professionalArr
     * @return
     * @throws LException
     */
    @RequestMapping("/professional_save.json")
    @ResponseBody
    public Result professional_save(@RequestParam(value="idArr") String[] idArr,
                                    @RequestParam(value="sortArr") String[] sortArr,
                                    @RequestParam(value="nameArr") String[] nameArr,
                                    @RequestParam(value = "professionalArr") String[] professionalArr) throws LException{

        List<Professional> professionalList = new ArrayList<Professional>();
        for (int i = 0; i < idArr.length; i++) {
            Professional professional = new Professional();
            professional.setId(idArr[i]);
            professional.setCollegeId(professionalArr[i]);
            professional.setName(nameArr[i]);
            professional.setSort(sortArr[i]);
            professionalList.add(professional);
        }
        professionalService.save(professionalList);
        return Result.success();


    }

}
