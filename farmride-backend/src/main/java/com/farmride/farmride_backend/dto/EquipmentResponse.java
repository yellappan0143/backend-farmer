package com.farmride.farmride_backend.dto;

import java.math.BigDecimal;

public class EquipmentResponse {


    private Long id;
    private String equipmentName;
    private String category;
    private String description;
    private BigDecimal hourlyPrice;
    private BigDecimal perAcrePrice;
    private String imageUrl;
    private boolean available;
    private String ownerName;


    public EquipmentResponse(Long id, String equipmentName, String category, String description, BigDecimal hourlyPrice, BigDecimal perAcrePrice, String imageUrl, boolean available, String ownerName) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.category = category;
        this.description = description;
        this.hourlyPrice = hourlyPrice;
        this.perAcrePrice = perAcrePrice;
        this.imageUrl = imageUrl;
        this.available = available;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
