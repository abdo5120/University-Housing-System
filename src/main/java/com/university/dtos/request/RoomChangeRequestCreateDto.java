package com.university.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomChangeRequestCreateDto {
    @NotBlank(message = "Reason is required")
    private String reason;

    @NotNull(message = "Student University ID is required")
    private String studentUniversityId;

    @NotNull(message = "Current room Number is required")
    private String currentRoomNumber;

    // Optional, can be null if the student has no specific room in mind
    private String requestedRoomNumber;
}
