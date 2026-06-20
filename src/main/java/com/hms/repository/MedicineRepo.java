package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer> {
    List<Medicine> findByNameContaining(String name);
    List<Medicine> findByCategory(String category);
}
