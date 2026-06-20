package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
