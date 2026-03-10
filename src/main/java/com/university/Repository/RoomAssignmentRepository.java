package com.university.Repository;

import com.university.entity.RoomAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, Long> {
}
