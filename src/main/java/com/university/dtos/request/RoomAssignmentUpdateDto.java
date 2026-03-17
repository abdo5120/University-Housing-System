package com.university.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomAssignmentUpdateDto {
    @NotNull(message = "Move-in date is required")
    private LocalDate moveOutDate;

    @NotNull(message = "Room Number is required")
    private String roomNumber;
}
