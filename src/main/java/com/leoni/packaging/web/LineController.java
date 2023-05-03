package com.leoni.packaging.web;

import com.leoni.packaging.enums.CableType;
import com.leoni.packaging.enums.Steering;
import com.leoni.packaging.model.Line;
import com.leoni.packaging.service.LineService;
import com.leoni.packaging.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class LineController {
    private final LineService lineService;
    private final RouteService routeService;

    @GetMapping(path = "lines")
    public String lines(Model model,
                        @RequestParam(name="search",required = false, defaultValue = "") String search,
                        @RequestParam(name="page",required = false, defaultValue = "1") int page,
                        @RequestParam(name="size",required = false, defaultValue = "5") int size){
        page = page > 0 ? --page:0;
        Page<Line> line = lineService.findLine(search, page, size);
        model.addAttribute("lines", line.stream().toList());
        model.addAttribute("search", search);
        model.addAttribute("totalPagesArr",new int[line.getTotalPages()]);
        model.addAttribute("totalPages",line.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("isFirstPage",line.isFirst());
        model.addAttribute("isLastPage",line.isLast());
        return "lines";
    }
    @GetMapping(path = {"lineForm", "lineForm/","lineForm/{lineId}"})
    public String lineForm(Model model, @PathVariable(name = "lineId",required = false) Optional<Long> lineId){
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("types", Arrays.stream(CableType.values()).toList());
        model.addAttribute("steering", Arrays.stream(Steering.values()).toList());
        if(lineId.isPresent()){
            Line line = lineService.findLineById(lineId.get());
            model.addAttribute("line", line);
        }else{
            model.addAttribute("line", new Line());
        }
        return "addLine";
    }
    @PostMapping(path = "line")
    public String postLine(Line line){
        if(line.getId()!=null){
            lineService.updateLine(line.getId(),line);
        }else{
            lineService.addLine(line);
        }
        return "redirect:/admin/lines";
    }

    @PostMapping(path = "deleteLine/{lineId}")
    public String deleteRoute(@PathVariable("lineId") Long lineId){
        lineService.deleteLineById(lineId);
        return "redirect:/admin/lines";
    }

}
