package com.university.mapping;

import com.university.dtos.response.AdminDto;
import com.university.entity.Admin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AdminMapper {
    AdminDto toDto(Admin admin);
    List<AdminDto> toDtoList(List<Admin> admins);
}