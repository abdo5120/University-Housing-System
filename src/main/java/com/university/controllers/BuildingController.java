package com.university.controllers;

import com.university.dtos.request.BuildingRequestDto;
import com.university.dtos.response.BuildingResponseDto;
import com.university.enums.BuildingGenderType;
import com.university.services.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    private final BuildingService buildingService;

    @PostMapping
    public ResponseEntity<BuildingResponseDto> createBuilding(
            @Valid @RequestBody BuildingRequestDto dto) {
        logger.info("POST /api/buildings — name: {}", dto.getBuildingName());
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(dto));
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponseDto>> getAllBuildings(
            @RequestParam(required = false) BuildingGenderType genderType) {
        logger.info("GET /api/buildings — genderType filter: {}", genderType);

        List<BuildingResponseDto> result = (genderType != null)
                ? buildingService.getBuildingsByGenderType(genderType)
                : buildingService.getAllBuildings();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponseDto> getBuildingById(@PathVariable Long id) {
        logger.info("GET /api/buildings/{}", id);
        return ResponseEntity.ok(buildingService.getBuildingDtoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponseDto> updateBuilding(
            @PathVariable Long id,
            @Valid @RequestBody BuildingRequestDto dto) {
        logger.info("PUT /api/buildings/{}", id);
        return ResponseEntity.ok(buildingService.updateBuilding(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        logger.info("DELETE /api/buildings/{}", id);
        buildingService.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }
}
