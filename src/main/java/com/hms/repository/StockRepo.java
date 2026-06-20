package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer> {
    List<Stock> findByMedicineMedicineId(int medicineId);
    List<Stock> findByQuantityLessThan(int threshold);
}
