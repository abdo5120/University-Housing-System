package com.university.dtos.request;

import com.university.enums.RoomChangeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomChangeRequestUpdateDto {
    @NotNull(message = "Status is required")
    private RoomChangeStatus status;

    @NotNull(message = "Reviewer By (Admin ID) is required")
    private Long reviewedById;

    private String requestedRoomNumber;
}
