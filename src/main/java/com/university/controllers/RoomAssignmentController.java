package com.university.controllers;

import com.university.dtos.request.RoomAssignmentCreateDto;
import com.university.dtos.response.RoomAssignmentResponseDto;
import com.university.services.RoomAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@RequiredArgsConstructor
public class RoomAssignmentController {

    private static final Logger logger = LoggerFactory.getLogger(RoomAssignmentController.class);

    private final RoomAssignmentService roomAssignmentService;

    @PostMapping
    public ResponseEntity<RoomAssignmentResponseDto> assignRoom(
            @Valid @RequestBody RoomAssignmentCreateDto dto) {
        logger.info("POST /api/room-assignments — universityId: {}, roomNumber: {}",
                dto.getUniversityId(), dto.getRoomNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomAssignmentService.assignRoom(dto));
    }

    @GetMapping
    public ResponseEntity<List<RoomAssignmentResponseDto>> getAllAssignments() {
        logger.info("GET /api/room-assignments");
        return ResponseEntity.ok(roomAssignmentService.getAllAssignments());
    }

    @GetMapping("/student/{studentId}/active")
    public ResponseEntity<RoomAssignmentResponseDto> getActiveAssignment(
            @PathVariable Long studentId) {
        logger.info("GET /api/room-assignments/student/{}/active", studentId);
        return ResponseEntity.ok(roomAssignmentService.getActiveAssignmentByStudentId(studentId));
    }

    @GetMapping("/student/{studentId}/history")
    public ResponseEntity<List<RoomAssignmentResponseDto>> getAssignmentHistory(
            @PathVariable Long studentId) {
        logger.info("GET /api/room-assignments/student/{}/history", studentId);
        return ResponseEntity.ok(roomAssignmentService.getAssignmentHistoryByStudentId(studentId));
    }

    @PatchMapping("/{assignmentId}/move-out")
    public ResponseEntity<RoomAssignmentResponseDto> moveOutStudent(
            @PathVariable Long assignmentId) {
        logger.info("PATCH /api/room-assignments/{}/move-out", assignmentId);
        return ResponseEntity.ok(roomAssignmentService.moveOutStudent(assignmentId));
    }
}
