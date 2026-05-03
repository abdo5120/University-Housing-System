package com.university.controllers;

import com.university.dtos.response.MaintenanceStaffDto;
import com.university.services.MaintenanceStaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-staff")
@RequiredArgsConstructor
public class MaintenanceStaffController {

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceStaffController.class);

    private final MaintenanceStaffService maintenanceStaffService;

    @GetMapping
    public ResponseEntity<List<MaintenanceStaffDto>> getAllStaff(
            @RequestParam(required = false) String specialization) {
        logger.info("GET /api/maintenance-staff — specialization filter: {}", specialization);

        List<MaintenanceStaffDto> result = (specialization != null && !specialization.isBlank())
                ? maintenanceStaffService.getStaffBySpecialization(specialization)
                : maintenanceStaffService.getAllStaff();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceStaffDto> getStaffById(@PathVariable Long id) {
        logger.info("GET /api/maintenance-staff/{}", id);
        return ResponseEntity.ok(maintenanceStaffService.getStaffDtoById(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateStaff(@PathVariable Long id) {
        logger.info("PATCH /api/maintenance-staff/{}/deactivate", id);
        maintenanceStaffService.deactivateStaff(id);
        return ResponseEntity.noContent().build();
    }
}
