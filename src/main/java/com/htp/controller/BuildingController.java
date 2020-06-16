package com.htp.controller;

import com.htp.service.BuildingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public String findAll(ModelMap modelMap){
        modelMap.addAttribute("buildings", buildingService.findAll());
        return "building/buildings";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long buildingId, ModelMap modelMap){
        modelMap.addAttribute("building", buildingService.findOne(buildingId));
        return "building/building";
    }

    @GetMapping("/search")
    public String searchBuilding(@RequestParam("query") String query, ModelMap modelMap){
        modelMap.addAttribute("buildings",buildingService.search(query));
        return "building/buildings";
    }

}
