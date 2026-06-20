package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.TestResult;

public interface TestResultRepo extends JpaRepository<TestResult, Integer> {
    List<TestResult> findByPatientPatientId(int patientId);
    List<TestResult> findByLabTestTestId(int testId);
}
