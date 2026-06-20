package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.LabTest;

public interface LabTestRepo extends JpaRepository<LabTest, Integer> {
}
