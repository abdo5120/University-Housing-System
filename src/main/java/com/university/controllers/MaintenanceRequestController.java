package com.university.controllers;

import com.university.dtos.request.MaintenanceRequestCreateDto;
import com.university.dtos.request.MaintenanceRequestUpdateDto;
import com.university.dtos.response.MaintenanceResponseDto;
import com.university.enums.MaintenanceStatus;
import com.university.services.MaintenanceRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-requests")
@RequiredArgsConstructor
public class MaintenanceRequestController {

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceRequestController.class);

    private final MaintenanceRequestService maintenanceRequestService;

    @PostMapping
    public ResponseEntity<MaintenanceResponseDto> createRequest(
            @Valid @RequestBody MaintenanceRequestCreateDto dto) {
        logger.info("POST /api/maintenance-requests — studentId: {}, issueType: {}",
                dto.getStudentId(), dto.getIssueType());
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceRequestService.createRequest(dto));
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceResponseDto>> getAllRequests(
            @RequestParam(required = false) MaintenanceStatus status) {
        logger.info("GET /api/maintenance-requests — status filter: {}", status);

        List<MaintenanceResponseDto> result = (status != null)
                ? maintenanceRequestService.getRequestsByStatus(status)
                : maintenanceRequestService.getAllRequests();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponseDto> getRequestById(@PathVariable Long id) {
        logger.info("GET /api/maintenance-requests/{}", id);
        return ResponseEntity.ok(maintenanceRequestService.getRequestById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MaintenanceResponseDto>> getRequestsByStudent(
            @PathVariable Long studentId) {
        logger.info("GET /api/maintenance-requests/student/{}", studentId);
        return ResponseEntity.ok(maintenanceRequestService.getRequestsByStudentId(studentId));
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<MaintenanceResponseDto>> getRequestsByStaff(
            @PathVariable Long staffId) {
        logger.info("GET /api/maintenance-requests/staff/{}", staffId);
        return ResponseEntity.ok(maintenanceRequestService.getRequestsByStaffId(staffId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MaintenanceResponseDto> updateRequest(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceRequestUpdateDto dto) {
        logger.info("PATCH /api/maintenance-requests/{} — status: {}, assignedStaffId: {}",
                id, dto.getStatus(), dto.getAssignedStaffId());
        return ResponseEntity.ok(maintenanceRequestService.updateRequest(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        logger.info("DELETE /api/maintenance-requests/{}", id);
        maintenanceRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
