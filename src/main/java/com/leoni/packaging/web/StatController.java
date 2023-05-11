package com.leoni.packaging.web;

import com.leoni.packaging.dto.CableResponseDto;
import com.leoni.packaging.dto.PageResponse;
import com.leoni.packaging.dto.RouteCablesCountDto;
import com.leoni.packaging.dto.StatisticsFilter;
import com.leoni.packaging.model.Cable;
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
        List<RouteCablesCountDto> routes = statisticService.findCablesByRoute(filter);
        model.addAttribute("routes", routes);
        model.addAttribute("filter", filter);
        return "index";
    }

    @GetMapping(path = "/api/cables/route/{routeId}")
    @ResponseBody
    public PageResponse<CableResponseDto> sendCablesByRoute(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                            @RequestParam(name = "size", defaultValue = "1", required = false) int size,
                                                            @PathVariable("routeId") Long routeId){
        page = page<=0? 0:page-1;
        PageResponse<CableResponseDto> cablesByRoute = statisticService.getCablesByRoute(routeId, page, size);
        cablesByRoute.setCurrentSize(size);
        cablesByRoute.setCurrentPage(page);
        return cablesByRoute;
    }


}
