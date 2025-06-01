package com.project.auction.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationMetaResponse {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;
}
