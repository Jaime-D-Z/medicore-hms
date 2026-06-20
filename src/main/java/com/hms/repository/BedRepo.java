package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Bed;

public interface BedRepo extends JpaRepository<Bed, Integer> {
    List<Bed> findByRoomRoomId(int roomId);
    List<Bed> findByStatus(String status);
    long countByStatus(String status);
}
