package com.university.dtos.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDto extends UserDto{
    private String department;
}
