package com.university.Repository;

import com.university.entity.MaintenanceRequest;
import com.university.enums.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {

    List<MaintenanceRequest> findAllByStudent_Id(Long studentId);

    List<MaintenanceRequest> findAllByAssignedStaff_Id(Long staffId);

    List<MaintenanceRequest> findAllByStatus(MaintenanceStatus status);
}
