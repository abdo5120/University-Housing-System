package com.university.mapping;

import com.university.Repository.AdminRepository;
import com.university.Repository.RoomRepository;
import com.university.Repository.StudentRepository;
import com.university.dtos.request.RoomAssignmentCreateDto;
import com.university.dtos.request.RoomAssignmentUpdateDto;
import com.university.dtos.response.RoomAssignmentResponseDto;
import com.university.entity.Admin;
import com.university.entity.Room;
import com.university.entity.RoomAssignment;
import com.university.entity.Student;
import com.university.exceptions.ResourceNotFoundException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        RoomMapper.class,
        StudentMapper.class,
        AdminMapper.class
})
public abstract class RoomAssignmentMapper {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AdminRepository adminRepository;

    public abstract RoomAssignmentResponseDto toDto(RoomAssignment entity);
    public abstract List<RoomAssignmentResponseDto> toDtoList(List<RoomAssignment> entities);


    @Mapping(target = "assignmentId", ignore = true)
    @Mapping(target = "assignmentDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "moveOutDate", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "assignedBy", ignore = true)
    public abstract RoomAssignment toEntity(RoomAssignmentCreateDto dto);

    @AfterMapping
    protected void setCreateRelations(RoomAssignmentCreateDto dto,
                                      @MappingTarget RoomAssignment entity) {
        Student student = studentRepository.findByUniversityId(dto.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with university ID: " + dto.getUniversityId()));

        Room room = roomRepository.findByRoomNumber(dto.getRoomNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with number: " + dto.getRoomNumber()));

        Admin admin = adminRepository.findById(dto.getAssignedById())
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + dto.getAssignedById()));

        entity.setStudent(student);
        entity.setRoom(room);
        entity.setAssignedBy(admin);
    }


    @Mapping(target = "assignmentId", ignore = true)
    @Mapping(target = "assignmentDate", ignore = true)
    @Mapping(target = "moveInDate", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "assignedBy", ignore = true)
    @Mapping(target = "room", ignore = true)
    public abstract void updateEntityFromDto(RoomAssignmentUpdateDto dto,
                                             @MappingTarget RoomAssignment entity);

    @AfterMapping
    protected void setUpdateRelations(RoomAssignmentUpdateDto dto,
                                      @MappingTarget RoomAssignment entity) {
        Room room = roomRepository.findByRoomNumber(dto.getRoomNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with number: " + dto.getRoomNumber()));
        entity.setRoom(room);
    }
}