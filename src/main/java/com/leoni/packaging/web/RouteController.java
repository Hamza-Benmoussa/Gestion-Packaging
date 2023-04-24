package com.leoni.packaging.web;

import com.leoni.packaging.model.Route;
import com.leoni.packaging.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class RouteController {
    public final RouteService routeService;

    @GetMapping(path = "routes")
    public String routes(Model model,
                        @RequestParam(name="search",required = false, defaultValue = "") String search,
                        @RequestParam(name="page",required = false, defaultValue = "1") int page,
                        @RequestParam(name="size",required = false, defaultValue = "5") int size){
        page = page > 0 ? --page:0;
        Page<Route> route = routeService.findRoute(search, page, size);
        model.addAttribute("routes", route.stream().toList());
        model.addAttribute("search", search);
        model.addAttribute("totalPagesArr",new int[route.getTotalPages()]);
        model.addAttribute("totalPages",route.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("isFirstPage",route.isFirst());
        model.addAttribute("isLastPage",route.isLast());
        return "routes";
    }
    @GetMapping(path = {"routeForm", "routeForm/","routeForm/{routeId}"})
    public String routeForm(Model model, @PathVariable(name = "routeId",required = false) Optional<Long> routeId){
        if(routeId.isPresent()){
            Route route = routeService.findRouteById(routeId.get());
            model.addAttribute("route", route);
        }else{
            model.addAttribute("route", new Route());
        }
        return "addRoute";
    }
    @PostMapping(path = "route")
    public String postRoute(Route route){
        if(route.getId()!=null){
            routeService.updateRoute(route.getId(),route);
        }else{
            routeService.addRoute(route);
        }
        return "redirect:/admin/routes";
    }

    @PostMapping(path = "deleteRoute/{routeId}")
    public String deleteRoute(@PathVariable("routeId") Long routeId){
        routeService.deleteRouteById(routeId);
        return "redirect:/admin/routes";
    }

}
