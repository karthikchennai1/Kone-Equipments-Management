package com.kone.EquipmentValidator;

import com.kone.Exceptions.EquipmentNotFoundException;
import com.kone.Exceptions.EquipmentNumberAlreadyExistException;
import com.kone.model.Equipments;
import com.kone.Exceptions.InvalidStatusException;
import com.kone.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EquipmentValidator {

    private final String RUNNING_STATUS = "Running";
    private final String STOPPED_STATUS = "Stopped";

    @Autowired
    private EquipmentService equipmentService;

    public void validateEquipments(Equipments equipments) throws IOException {
        validateEquipmentNumber(equipments);
        validateStatus(equipments);
    }

    public void validateEquipmentExist(List<Equipments> equipmentList) {
        if (equipmentList.isEmpty()) {
            throw new EquipmentNotFoundException("Requested Equipment Not Found EquipmentNumber =" + equipmentList);
        }
    }

    private void validateEquipmentNumber(Equipments equipments) throws IOException {
        List<Equipments> allEquipments = equipmentService.getAllEquipment();
        for (Equipments alreadyExistEquipments : allEquipments) {
            if (alreadyExistEquipments.getEquipmentNumber() == equipments.getEquipmentNumber()) {
                throw new EquipmentNumberAlreadyExistException("Duplicate Equipment Found EquipmentNumber =" + equipments.getEquipmentNumber());
            }
        }
    }

    private void validateStatus(Equipments equipments) {
        if (equipments.getStatus() != null && (!equipments.getStatus().equalsIgnoreCase(RUNNING_STATUS) && !equipments.getStatus().equalsIgnoreCase(STOPPED_STATUS))) {
            throw new InvalidStatusException("Invalid status in the request 'status'=" + equipments.getStatus());
        }
    }
}
