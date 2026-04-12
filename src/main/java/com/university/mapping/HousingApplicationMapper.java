package com.university.mapping;

import com.university.Repository.AdminRepository;
import com.university.Repository.StudentRepository;
import com.university.dtos.request.HousingApplicationRequestCreateDto;
import com.university.dtos.request.HousingApplicationRequestReviewDto;
import com.university.dtos.response.HousingApplicationResponseDto;
import com.university.entity.Admin;
import com.university.entity.HousingApplication;
import com.university.entity.Student;
import com.university.exceptions.ResourceNotFoundException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        StudentMapper.class,
        AdminMapper.class
})
public abstract class HousingApplicationMapper {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    public abstract HousingApplicationResponseDto toDto(HousingApplication housingApplication);
    public abstract List<HousingApplicationResponseDto> toDtoList(List<HousingApplication> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "submissionDate", ignore = true)
    @Mapping(target = "reviewDate", ignore = true)
    @Mapping(target = "rejectionReason", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "reviewedBy", ignore = true)
    public abstract HousingApplication toEntity(HousingApplicationRequestCreateDto dto);

    @AfterMapping
    protected void setCreateRelations(HousingApplicationRequestCreateDto dto,
                                      @MappingTarget HousingApplication entity) {
        Student student = studentRepository.findByUniversityId(dto.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with university ID: " + dto.getUniversityId()));        entity.setStudent(student);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentationPath", ignore = true)
    @Mapping(target = "submissionDate", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "reviewedBy", ignore = true)
    @Mapping(target = "reviewDate", expression = "java(java.time.LocalDate.now())")
    public abstract void reviewEntityFromDto(HousingApplicationRequestReviewDto dto,
                                             @MappingTarget HousingApplication entity);

    @AfterMapping
    protected void setReviewRelations(HousingApplicationRequestReviewDto dto,
                                      @MappingTarget HousingApplication entity) {
        Admin admin = adminRepository.findById(dto.getReviewedById())
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + dto.getReviewedById()));        entity.setReviewedBy(admin);
    }
}
