package com.leoni.packaging.web;

import com.leoni.packaging.dto.CableResponseDto;
import com.leoni.packaging.dto.PageResponse;
import com.leoni.packaging.dto.RouteCablesCountDto;
import com.leoni.packaging.dto.StatisticsFilter;
import com.leoni.packaging.model.Cable;
import com.leoni.packaging.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class StatController {
    private final StatisticService statisticService;

    @GetMapping(path = "statistics")
    public String index(Model model, StatisticsFilter filter){
        List<RouteCablesCountDto> routes = statisticService.findCablesByRoute(filter);
        model.addAttribute("routes", routes);
        model.addAttribute("filter", filter);
        return "index";
    }

    @GetMapping(path = "/api/cables/route/{routeId}")
    @ResponseBody
    public List<CableResponseDto> sendCablesByRoute(@PathVariable("routeId") Long routeId,
                                                    @RequestParam("dateDebut")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut,
                                                    @RequestParam("dateFin")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFin){
        dateDebut = dateDebut==null?LocalDate.now():dateDebut;
        dateFin = dateFin==null?LocalDate.now():dateFin;
        List<CableResponseDto> cablesByRoute = statisticService.getCablesByRoute(routeId, dateDebut, dateFin);
        return cablesByRoute;
    }


}
