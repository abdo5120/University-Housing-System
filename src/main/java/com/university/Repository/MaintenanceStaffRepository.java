package com.university.Repository;

import com.university.entity.MaintenanceStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceStaffRepository extends JpaRepository<MaintenanceStaff, Long> {
    List<MaintenanceStaff> findBySpecialization(String specialization);
}
