package com.htp.controller;

import com.htp.aspect.LogAspect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @GetMapping
    public String stat(ModelMap modelMap){
        modelMap.addAttribute("statistic", LogAspect.showStatistic());
        return "statistic";
    }
}
