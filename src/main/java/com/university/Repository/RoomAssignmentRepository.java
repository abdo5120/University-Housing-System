package com.university.Repository;

import com.university.entity.RoomAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, Long> {
    boolean existsByStudent_IdAndRoom_RoomIdAndMoveOutDateIsNull(Long id, Long roomId);

    boolean existsByStudent_IdAndMoveOutDateIsNull(Long id);

    List<RoomAssignment> findAllByStudent_Id(Long studentId);

    Optional<RoomAssignment> findByStudent_IdAndMoveOutDateIsNull(Long studentId);
}
