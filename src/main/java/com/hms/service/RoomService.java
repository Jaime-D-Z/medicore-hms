package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Room;
import com.hms.repository.RoomRepo;

@Service
public class RoomService {
    @Autowired
    private RoomRepo repo;

    public void save(Room r) { repo.save(r); }
    public List<Room> getAll() { return repo.findAll(); }
    public Room getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<Room> getAvailable() { return repo.findByIsAvailable(true); }
    public long countAvailable() { return repo.countByIsAvailable(true); }
    public long countTotal() { return repo.count(); }
}
