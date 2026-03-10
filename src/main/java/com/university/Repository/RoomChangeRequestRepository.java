package com.university.Repository;

import com.university.entity.RoomChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomChangeRequestRepository extends JpaRepository<RoomChangeRequest, Integer> {

}
