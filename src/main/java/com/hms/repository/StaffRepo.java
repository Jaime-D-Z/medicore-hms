package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {
    List<Staff> findByRoleRoleId(int roleId);
    List<Staff> findByDepartmentDeptId(int deptId);
    long countByRoleRoleId(int roleId);
}
