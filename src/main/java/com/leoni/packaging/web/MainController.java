package com.leoni.packaging.web;

import com.leoni.packaging.dto.StatisticsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Slf4j
public class MainController {

    @GetMapping(path = "statistics")
    public String index(Model model, StatisticsFilter filter){
        model.addAttribute("filter",filter);
        System.out.println(filter);
        return "index";
    }


}
