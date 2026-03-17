package com.university.mapping;

import com.university.dtos.request.BuildingRequestDto;
import com.university.dtos.response.BuildingResponseDto;
import com.university.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoomMapper.class})
public interface BuildingMapper {

    BuildingResponseDto toDto(Building building);

    List<BuildingResponseDto> toDtoList(List<Building> buildings);


    @Mapping(target = "buildingId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "maintenanceRequests", ignore = true)
    Building toEntity(BuildingRequestDto dto);
/*
    @Mapping(target = "buildingId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "maintenanceRequests", ignore = true)
    void updateEntityFromDto(BuildingRequestDto dto, @MappingTarget Building building);
*/
}
