package com.leoni.packaging.dto;

import com.leoni.packaging.model.Cable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
public class CableResponseDto {
    private String barCode;
    private LocalDateTime started;
    private LocalDateTime completed;
    private long duration;
    private String lineName;
    private String routeName;

    public static CableResponseDto getCableResponseDtoFromCable(Cable cable){
        return CableResponseDto.builder()
                .barCode(cable.getBarCode())
                .started(cable.getStarted())
                .completed(cable.getCompleted())
                .duration(cable.getDuration())
                .lineName(cable.getLine().getLineName())
                .routeName(cable.getLine().getRoute().getRouteV())
                .build();
    }

}
