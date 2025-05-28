package com.prototype.restaurante_el_negro.dto;

import com.prototype.restaurante_el_negro.enums.ProductStatus;
import com.prototype.restaurante_el_negro.models.ProductBase;

import java.sql.Date;

public class ProductBaseDto {
    private Integer id;
    private String name;
    private String description;
    private String unitOfMeasure;
    private Integer quantity;
    private Integer price;
    private Date creationDate;
    private Date modificationDate;
    private String imageUrl;
    private ProductStatus status = ProductStatus.AVAILABLE;
    private String category;

    public ProductBaseDto() {
    }

    public ProductBaseDto(Integer id, String name, String description, String unitOfMeasure, Integer quantity, Integer price, Date creationDate, Date modificationDate, String imageUrl, ProductStatus status, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
        this.price = price;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.imageUrl = imageUrl;
        this.status = status;
        this.category = category;
    }

    public ProductBaseDto(ProductBase productBase) {
        this.id = productBase.getProductId();
        this.name = productBase.getName();
        this.description = productBase.getDescription();
        this.unitOfMeasure = productBase.getUnitOfMeasure();
        this.quantity = productBase.getQuantity();
        this.price = productBase.getPrice();
        this.creationDate = productBase.getCreationDate();
        this.modificationDate = productBase.getModificationDate();
        this.category = productBase.getCategory() != null? productBase.getCategory().getName():null;
        this.imageUrl = productBase.getProductImage() != null?
                productBase.getProductImage().getUrlImage()
                :null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
