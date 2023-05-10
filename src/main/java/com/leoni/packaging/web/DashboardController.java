package com.leoni.packaging.web;

import com.leoni.packaging.dto.CablesByHourDto;
import com.leoni.packaging.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    private final StatisticService statisticService;

    @GetMapping(path = "dashboard")
    public String index(Model model){
        List<CablesByHourDto> countCablesForEachHour = statisticService.getCountCablesForEachHour();
        model.addAttribute("cablesCount", countCablesForEachHour);
        long totalCables=0L;
        for (CablesByHourDto r: countCablesForEachHour) {
            totalCables+=r.getCablesCount();
        }
        model.addAttribute("totalCables", totalCables);
        return "dashboardPage";
    }


}
