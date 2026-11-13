package com.farmride.farmride_backend.service;


import com.farmride.farmride_backend.dto.EquipmentRequest;
import com.farmride.farmride_backend.entity.Equipment;
import com.farmride.farmride_backend.entity.User;
import com.farmride.farmride_backend.repository.EquipmentRepository;
import com.farmride.farmride_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    public EquipmentService(EquipmentRepository equipmentRepository, UserRepository userRepository) {
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
    }

    public Equipment addEquipment(String identifier, EquipmentRequest request){

        User owner = userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByMobileNumber(identifier))
                        .orElseThrow(() -> new RuntimeException("User not forund"));

        if(!"EQUIPMENT_OWNER".equals(owner.getRole())){
                throw new RuntimeException("Only equipment owner can add equipment");
        }

        Equipment equipment = new Equipment();

        equipment.setEquipmentName(request.getEquipmentName());
        equipment.setEquipmentName(request.getEquipmentName());
        equipment.setCategory(request.getCategory());
        equipment.setDescription(request.getDescription());
        equipment.setHourlyPrice(request.getHourlyPrice());
        equipment.setPerAcrePrice(request.getPerAcrePrice());
        equipment.setImageUrl(request.getImageUrl());
        equipment.setOwner(owner);

        return equipmentRepository.save(equipment);





    }
}
