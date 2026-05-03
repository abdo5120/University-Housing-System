package com.university.Repository;

import com.university.entity.HousingApplication;
import com.university.enums.HousingApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingApplicationRepository extends JpaRepository<HousingApplication, Long> {

    List<HousingApplication> findByStudentId(Long id);

    boolean existsByStudentIdAndStatus(Long id, HousingApplicationStatus housingApplicationStatus);
}
