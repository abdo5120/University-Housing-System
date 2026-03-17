package com.university.dtos.response;

import com.university.enums.BuildingGenderType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BuildingResponseDto {
    private Long buildingId;
    private String buildingName;
    private String location;
    private Integer totalRooms;
    private Integer totalCapacity;
    private BuildingGenderType genderType;
    private LocalDateTime createdAt;
    private List<RoomResponseDto> rooms;
}
