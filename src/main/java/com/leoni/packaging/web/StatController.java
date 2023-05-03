package com.leoni.packaging.web;

import com.leoni.packaging.dto.PackageLinesCablesCountDto;
import com.leoni.packaging.dto.StatisticsFilter;
import com.leoni.packaging.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class StatController {
    private final StatisticService statisticService;

    @GetMapping(path = "statistics")
    public String index(Model model, StatisticsFilter filter){
        List<PackageLinesCablesCountDto> packages = statisticService.findPackages(filter);
        model.addAttribute("packages", packages);
        model.addAttribute("filter", filter);
        return "index";
    }


}
