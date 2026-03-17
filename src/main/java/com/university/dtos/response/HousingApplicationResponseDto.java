package com.university.dtos.response;

import com.university.enums.HousingApplicationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HousingApplicationResponseDto {

    private Long id;
    private String documentationPath;
    private HousingApplicationStatus status;
    private LocalDate submissionDate;
    private LocalDate reviewDate;
    private String rejectionReason;

    private StudentDto student;
    private AdminDto reviewedBy;
}
