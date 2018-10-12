package com.cqcet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by 那个谁 on 2018/9/11.
 * 首页
 */
@Controller
@RequestMapping(value = "/show/index",method={RequestMethod.GET})
public class IndexController {

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

}
