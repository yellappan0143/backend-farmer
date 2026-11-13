package com.farmride.farmride_backend.repository;

import com.farmride.farmride_backend.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByOwnerId(Long ownerId);

    List<Equipment> findByAvailableTrue();
}

// What do these methods do?
// - JpaRepository<Equipment, Long> – provides built-in database operations.
// - findByOwnerId() – retrieves equipment belonging to a particular owner.
// - findByAvailableTrue() – retrieves only available equipment.
