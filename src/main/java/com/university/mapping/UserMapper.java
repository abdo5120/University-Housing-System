package com.university.mapping;

import com.university.dtos.response.UserDto;
import com.university.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}