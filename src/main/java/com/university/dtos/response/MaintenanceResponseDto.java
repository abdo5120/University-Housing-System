package com.university.dtos.response;

import com.university.enums.MaintenanceStatus;

import java.time.LocalDateTime;

public class MaintenanceResponseDto {
    private Long id;
    private String issueType;
    private String description;
    private MaintenanceStatus status;
    private LocalDateTime submissionDate;
    private LocalDateTime resolvedDate;
    private String notes;

    private StudentDto student;
    private MaintenanceStaffDto assignedStaff;
    private BuildingResponseDto building;
    private RoomResponseDto room;
}
