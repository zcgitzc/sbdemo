package com.zark.sbproject.boot.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author zark
 * @description
 * @date 2019-08-27 22:02
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        Map<String, Boolean> map = Maps.newHashMap();
        map.put("hello", true);
        map.put("world", true);

        List<String> list = Lists.newArrayList("hello", "world");

        model.addAttribute("hello", "Hello world");
        model.addAttribute("map", JSON.toJSONString(map));
        model.addAttribute("list", JSON.toJSONString(list));

        return "index";
    }
}
