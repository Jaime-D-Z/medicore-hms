package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {
    List<Room> findByDepartmentDeptId(int deptId);
    List<Room> findByIsAvailable(boolean isAvailable);
    List<Room> findByRoomType(String roomType);
    long countByIsAvailable(boolean isAvailable);
}
