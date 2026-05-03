package com.university.Repository;

import com.university.entity.Room;
import com.university.enums.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);

    List<Room> findByStatus(RoomStatus status);

    List<Room> findByBuilding_BuildingId(Long buildingId);

    boolean existsByRoomNumber(@NotBlank(message = "Room number is required") @Size(max = 30) String roomNumber);
}
