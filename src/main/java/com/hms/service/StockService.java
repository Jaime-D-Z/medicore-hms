package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Stock;
import com.hms.repository.StockRepo;

@Service
public class StockService {
    @Autowired
    private StockRepo repo;

    public void save(Stock s) { repo.save(s); }
    public List<Stock> getAll() { return repo.findAll(); }
    public Stock getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<Stock> getLowStock(int threshold) { return repo.findByQuantityLessThan(threshold); }
}
