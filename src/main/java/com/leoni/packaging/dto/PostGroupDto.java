package com.leoni.packaging.dto;

import com.leoni.packaging.model.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PostGroupDto {
    private Long id;
    private String name;
    private int workingTime;

    public Group fromPostGroupDto(){
        List<WorkingTime> workingTimes = new ArrayList<>();
        workingTimes.add(new WorkingTime(LocalTime.of(6,0), LocalTime.of(13,59)));
        workingTimes.add(new WorkingTime(LocalTime.of(14,0), LocalTime.of(21,59)));
        workingTimes.add(new WorkingTime(LocalTime.of(22,0), LocalTime.of(5,59)));

        return Group.builder()
                .id(this.id)
                .name(this.name)
                .workingTime(workingTimes.get(workingTime))
                .build();
    }

    public static PostGroupDto fromGroup(Group group){
        LocalTime startTime = group.getWorkingTime().getStartTime();
        LocalTime endTime = group.getWorkingTime().getEndTime();
        int workingTime = 0;
        if(startTime.isAfter(LocalTime.of(5,59)) && endTime.isBefore(LocalTime.of(14,0)))
            workingTime = 0;
        if(startTime.isAfter(LocalTime.of(13,59)) && endTime.isBefore(LocalTime.of(22,0)))
            workingTime = 1;
        if(startTime.isAfter(LocalTime.of(21,59)) && endTime.isBefore(LocalTime.of(6,0)))
            workingTime = 2;
        return PostGroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .workingTime(workingTime)
                .build();
    }
}
