package com.kone.service;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.Response;
import com.kone.EquipmentValidator.EquipmentValidator;
import com.kone.model.Equipments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    @Autowired
    private Database db;

    @Autowired
    private EquipmentValidator equipmentValidator;

    public Response insertEquipments(Equipments equipments) throws IOException {
        equipmentValidator.validateEquipments(equipments);
        return db.post(equipments);
    }

    public ModelAndView getEquipmentByEquipmentNumber(Long equipmentNumber) {
        // Get all documents from karthidb
        List<Equipments> equipmentList = null;
        // create Index
        // Here is create a design doc named designdoc
        // A view named querybyEquipmentNumberView
        // and an index named equipmentNumber
        db.createIndex("querybyEquipmentNumberView", "designdoc", "json",
                new IndexField[]{new IndexField("equipmentNumber", IndexField.SortOrder.asc)});
        equipmentList = db.findByIndex("{\"equipmentNumber\" : " + equipmentNumber + "}", Equipments.class);
        equipmentValidator.validateEquipmentExist(equipmentList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("EquipmentData");
        modelAndView.addObject("equipment", equipmentList.get(0));

        return modelAndView;
    }

    public ModelAndView getEquipmentByLimit(Integer limit) throws IOException {
        List<Equipments> equipmentList = getAllEquipment();
        if (limit > equipmentList.size()) {
            limit = equipmentList.size();
        }
        List<Equipments> limitedEquipmentList = equipmentList.stream().limit(limit).collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("EquipmentsData");
        modelAndView.addObject("equipments", limitedEquipmentList);

        return modelAndView;
    }

    public List<Equipments> getAllEquipment() throws IOException {
        List<Equipments> allEquipmentList = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Equipments.class);
        return allEquipmentList;
    }

}
