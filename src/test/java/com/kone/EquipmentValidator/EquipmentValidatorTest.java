package com.kone.EquipmentValidator;

import com.kone.Exceptions.EquipmentNotFoundException;
import com.kone.Exceptions.EquipmentNumberAlreadyExistException;
import com.kone.Exceptions.InvalidStatusException;
import com.kone.model.Equipments;
import com.kone.service.EquipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EquipmentValidatorTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentValidator equipmentValidator;

    @Test(expected = EquipmentNotFoundException.class)
    public void testValidateEquipmentNotExist() {

        List<Equipments> equipmentsList = new ArrayList<>();
        equipmentValidator.validateEquipmentExist(equipmentsList);
    }

    @Test
    public void testValidateEquipmentExist() {
        List<Equipments> equipmentsList = new ArrayList<>();
        equipmentsList.add(new Equipments());
        equipmentValidator.validateEquipmentExist(equipmentsList);
    }

    @Test(expected = EquipmentNumberAlreadyExistException.class)
    public void testValidateEquipmentNumberAlreadyExist() throws IOException {
        Equipments equipments = new Equipments();
        equipments.setEquipmentNumber(25l);
        List<Equipments> equipmentsList = new ArrayList<>();
        equipmentsList.add(equipments);
        when(equipmentService.getAllEquipment()).thenReturn(equipmentsList);
        equipmentValidator.validateEquipments(equipments);
    }

    @Test(expected = EquipmentNumberAlreadyExistException.class)
    public void testValidateEquipmentNumberNotAlreadyExist() throws IOException {
        Equipments equipments = new Equipments();
        equipments.setEquipmentNumber(25l);
        List<Equipments> equipmentsList = new ArrayList<>();
        equipmentsList.add(equipments);
        when(equipmentService.getAllEquipment()).thenReturn(equipmentsList);
        equipments.setEquipmentNumber(26l);
        equipmentValidator.validateEquipments(equipments);
    }

    @Test(expected = InvalidStatusException.class)
    public void testValidateInvalidRunningStatus() throws IOException {
        Equipments equipments = new Equipments();
        equipments.setStatus("Run");
        equipmentValidator.validateEquipments(equipments);
    }

    @Test(expected = InvalidStatusException.class)
    public void testValidateInvalidStoppedStatus() throws IOException {
        Equipments equipments = new Equipments();
        equipments.setStatus("Stop");
        equipmentValidator.validateEquipments(equipments);
    }

    @Test
    public void testValidateValidStoppedStatus() throws IOException {
        Equipments equipments = new Equipments();
        equipments.setStatus("Stopped");
        equipmentValidator.validateEquipments(equipments);
    }

    @Test
    public void testValidateValidRunningStatus() throws IOException {
        Equipments equipments = new Equipments();
        equipments.setStatus("Running");
        equipmentValidator.validateEquipments(equipments);
    }

}