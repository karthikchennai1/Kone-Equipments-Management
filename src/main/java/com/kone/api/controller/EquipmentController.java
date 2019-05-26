package com.kone.api.controller;

import com.cloudant.client.api.model.Response;
import com.kone.model.Equipments;
import com.kone.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;


    @RequestMapping(method = RequestMethod.GET, value = "/{equipmentNumber}")
    public @ResponseBody
    ModelAndView getEquipmentByEquipmentNumber(@PathVariable Long equipmentNumber) {
        return equipmentService.getEquipmentByEquipmentNumber(equipmentNumber);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getEquipmentByLimit(@RequestParam("limit") Integer limit) throws IOException {
        return equipmentService.getEquipmentByLimit(limit);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEquipment(@Valid @RequestBody Equipments equipments) throws IOException {
        Response response = null;
        if (equipments != null) {
            response = equipmentService.insertEquipments(equipments);
        }
        return response.getId();
    }
}
