package com.kone.api.controller;

import com.cloudant.client.api.model.Response;
import com.kone.model.Equipments;
import com.kone.service.EquipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EquipmentControllerTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    EquipmentController equipmentController;


    @Test
    public void testSaveEquipment() throws IOException {
        Response response = new Response();
        when(equipmentService.insertEquipments(any(Equipments.class))).thenReturn(response);

        Equipments equipments = new Equipments();
        assertThat(equipmentController.saveEquipment(equipments), is(response.getId()));
    }
}