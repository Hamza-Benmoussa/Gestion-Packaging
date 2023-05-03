package com.leoni.packaging.web;

import com.leoni.packaging.model.Group;
import com.leoni.packaging.model.Line;
import com.leoni.packaging.model.Objective;
import com.leoni.packaging.service.GroupService;
import com.leoni.packaging.service.LineService;
import com.leoni.packaging.service.ObjectiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class ObjectiveController {
    public final ObjectiveService objectiveService;
    public final GroupService groupService;

    @GetMapping(path = "objectives")
    public String objectives(Model model,
                        @RequestParam(name="page",required = false, defaultValue = "1") int page,
                        @RequestParam(name="size",required = false, defaultValue = "5") int size){
        page = page > 0 ? --page:0;
        Page<Objective> objective = objectiveService.findObjective(page, size);
        model.addAttribute("objectives", objective.stream().toList());
        model.addAttribute("totalPagesArr",new int[objective.getTotalPages()]);
        model.addAttribute("totalPages",objective.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("isFirstPage",objective.isFirst());
        model.addAttribute("isLastPage",objective.isLast());
        return "objectives";
    }
    @GetMapping(path = {"objectiveForm", "objectiveForm/","objectiveForm/{objectiveId}"})
    public String objectiveForm(Model model, @PathVariable(name = "objectiveId",required = false) Optional<Long> objectiveId){
        List<Group> groups = groupService.findGroups();
        model.addAttribute("groups", groups);
        if(objectiveId.isPresent()){
            Objective objective = objectiveService.findObjectiveById(objectiveId.get());
            model.addAttribute("objective", objective);
        }else{
            model.addAttribute("objective", new Objective());
        }
        return "addObjective";
    }
    @PostMapping(path = "objective")
    public String postObjective(Objective objective){
        if(objective.getId()!=null){
            objectiveService.updateObjective(objective.getId(),objective);
        }else{
            objectiveService.addObjective(objective);
        }
        return "redirect:/admin/objectives";
    }

    @PostMapping(path = "deleteObjective/{objectiveId}")
    public String deleteRoute(@PathVariable("objectiveId") Long objectiveId){
        objectiveService.deleteObjectiveById(objectiveId);
        return "redirect:/admin/objectives";
    }

}
