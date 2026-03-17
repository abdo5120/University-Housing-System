package com.university.dtos.response;

import com.university.enums.RoomStatus;
import lombok.Data;

@Data
public class RoomResponseDto {
    private Long roomId;
    private String roomNumber;
    private Integer capacity;
    private Integer occupiedBeds;
    private RoomStatus status;
    private Long buildingId;
    private String buildingName;
}
