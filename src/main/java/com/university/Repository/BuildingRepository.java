package com.university.Repository;

import com.university.entity.Building;
import com.university.enums.BuildingGenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Optional<Building> findByBuildingName(String buildingName);

    boolean existsByBuildingName(String buildingName);

    List<Building> findByGenderType(BuildingGenderType genderType);
}
