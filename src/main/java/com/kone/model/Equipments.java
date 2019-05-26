package com.kone.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Equipments {

    @NotNull
    private Long equipmentNumber;

    @NotNull
    private String address;

    @NotNull
    private Date startDate;

    private Date endDate;

    @NotNull
    private String status;

    public void setEquipmentNumber(Long equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getEquipmentNumber() {
        return equipmentNumber;
    }

    public String getAddress() {
        return address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
