package com.leoni.packaging.web;

import com.leoni.packaging.dto.PostGroupDto;
import com.leoni.packaging.dto.WorkingTime;
import com.leoni.packaging.model.Group;
import com.leoni.packaging.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final List<String> workingTimes = List.of("06:00 -- 13:59", "14:00 -- 21:59", "22:00 -- 05:59");

    @GetMapping(path = "groups")
    public String groups(Model model,
                        @RequestParam(name="search",required = false, defaultValue = "") String search,
                        @RequestParam(name="page",required = false, defaultValue = "1") int page,
                        @RequestParam(name="size",required = false, defaultValue = "5") int size){
        page = page > 0 ? --page:0;
        Page<Group> group = groupService.findGroups(search, page, size);
        model.addAttribute("groups", group.stream().map(PostGroupDto::fromGroup).collect(Collectors.toList()));
        model.addAttribute("workingTimes", workingTimes);
        model.addAttribute("search", search);
        model.addAttribute("totalPagesArr",new int[group.getTotalPages()]);
        model.addAttribute("totalPages",group.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("isFirstPage",group.isFirst());
        model.addAttribute("isLastPage",group.isLast());
        return "groups";
    }
    @GetMapping(path = {"groupForm", "groupForm/","groupForm/{groupId}"})
    public String groupForm(Model model, @PathVariable(name = "groupId",required = false) Optional<Long> groupId){
        if(groupId.isPresent()){
            Group group = groupService.findGroupById(groupId.get());
            model.addAttribute("group", PostGroupDto.fromGroup(group));
        }else{
            model.addAttribute("group", PostGroupDto.fromGroup(new Group()));
        }
        model.addAttribute("workingTimes", workingTimes);
        return "addGroup";
    }
    @PostMapping(path = "group")
    public String postGroup(PostGroupDto groupDto){
        Group group = groupDto.fromPostGroupDto();
        if(group.getId()!=null){
            groupService.updateGroup(group.getId(),group);
        }else{
            groupService.addGroup(group);
        }
        return "redirect:/admin/groups";
    }

    @PostMapping(path = "deleteGroup/{groupId}")
    public String deleteRoute(@PathVariable("groupId") Long groupId){
        groupService.deleteGroupById(groupId);
        return "redirect:/admin/groups";
    }

}
