package com.university.controllers;

import com.university.dtos.response.StudentDto;
import com.university.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        logger.info("GET /api/students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        logger.info("GET /api/students/{}", id);
        return ResponseEntity.ok(studentService.getStudentDtoById(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateStudent(@PathVariable Long id) {
        logger.info("PATCH /api/students/{}/deactivate", id);
        studentService.deactivateStudent(id);
        return ResponseEntity.noContent().build();
    }
}