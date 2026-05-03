package com.university.mapping;

import com.university.Repository.BuildingRepository;
import com.university.dtos.request.RoomRequestDto;
import com.university.dtos.response.RoomResponseDto;
import com.university.entity.Building;
import com.university.entity.Room;
import com.university.exceptions.ResourceNotFoundException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoomMapper {

    @Autowired
    private BuildingRepository buildingRepository;

    @Mapping(source = "building.buildingId", target = "buildingId")
    @Mapping(source = "building.buildingName", target = "buildingName")
    public abstract RoomResponseDto toDto(Room room);

    public abstract List<RoomResponseDto> toDtoList(List<Room> rooms);

    @Mapping(target = "roomId", ignore = true)
    @Mapping(target = "building", ignore = true)
    @Mapping(target = "roomAssignments", ignore = true)
    @Mapping(target = "requestedRoomChanges", ignore = true)
    @Mapping(target = "currentRoomChanges", ignore = true)
    @Mapping(target = "maintenanceRequests", ignore = true)
    public abstract Room toEntity(RoomRequestDto dto);

    @AfterMapping
    protected void setBuilding(RoomRequestDto dto, @MappingTarget Room room) {
        Building building = buildingRepository.findByBuildingName(dto.getBuildingName())
                .orElseThrow(() -> new ResourceNotFoundException("Building not found with name: " + dto.getBuildingName()));
        room.setBuilding(building);
    }
}
