package com.leoni.packaging.web;

import com.leoni.packaging.model.Objective;
import com.leoni.packaging.service.ObjectiveService;
import com.leoni.packaging.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class ObjectiveController {
//    public final ObjectiveService objectiveService;

    @GetMapping(path = "objectives")
    public String objectives(Model model,@RequestParam(name="search",required = false,defaultValue = "") String search){
        model.addAttribute("search", search);
        return "objectives";
    }
    @GetMapping(path = "add-objective")
    public String objectiveForm(Model model){
        model.addAttribute("objective", new Objective());
        return "addObjective";
    }
    @PostMapping(path = "add-objective")
    public String addObjective(Model model,@RequestParam("objective") String objective){
        return "redirect:/objectives";
    }
    @PostMapping(path = "add-objective/{objectiveId}")
    public String updateObjective(Model model,
                              @PathVariable("objectiveId") Long objectiveId,
                              @RequestParam("objective") String objective){
        return "redirect:/objectives";
    }
    @PostMapping(path = "delete-objective/{objectiveId}")
    public String deleteObjective(@PathVariable("objectiveId") Long objectiveId){
        return "redirect:/objectives";
    }
}
