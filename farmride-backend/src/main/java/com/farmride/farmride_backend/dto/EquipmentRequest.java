package com.farmride.farmride_backend.dto;

import java.math.BigDecimal;

public class EquipmentRequest {


    private String equipmentName;
    private String category;
    private String description;
    private BigDecimal hourlyPrice;
    private BigDecimal perAcrePrice;
    private String imageUrl;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(BigDecimal hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public BigDecimal getPerAcrePrice() {
        return perAcrePrice;
    }

    public void setPerAcrePrice(BigDecimal perAcrePrice) {
        this.perAcrePrice = perAcrePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
