package com.leoni.packaging.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data @Builder
public class PageResponse<T>{
    private List<T> content;
    private int totalPages;
    private int currentPage;
    private int currentSize;
    private boolean isFirstPage;
    private boolean isLastPage;

    public static PageResponse fromPage(Page page, List content){
        return PageResponse.builder()
                .content(content)
                .totalPages(page.getTotalPages())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .build();
    }
}
