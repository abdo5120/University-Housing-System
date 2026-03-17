package com.university.mapping;

import com.university.dtos.response.MaintenanceStaffDto;
import com.university.entity.MaintenanceStaff;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MaintenanceStaffMapper {
    MaintenanceStaffDto toDto(MaintenanceStaff staff);
    List<MaintenanceStaffDto> toDtoList(List<MaintenanceStaff> staffList);
}