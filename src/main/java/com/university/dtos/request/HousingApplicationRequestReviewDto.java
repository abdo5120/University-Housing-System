package com.university.dtos.request;

import com.university.enums.HousingApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HousingApplicationRequestReviewDto {
    @NotNull(message = "Status is required")
    private HousingApplicationStatus status;

    private String rejectionReason;

    @NotNull(message = "Reviewer ID is required")
    private Long reviewedById;
}
