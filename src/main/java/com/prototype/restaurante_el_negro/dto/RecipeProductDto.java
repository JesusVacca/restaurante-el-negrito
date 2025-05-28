package com.prototype.restaurante_el_negro.dto;


import com.prototype.restaurante_el_negro.models.RecipeProduct;

public class RecipeProductDto {
    private Integer id;
    private String unitOfMeasure;
    private Integer quantity;
    private Integer productId;
    private String productName;


    public RecipeProductDto() {
    }

    public RecipeProductDto(Integer id, String unitOfMeasure, Integer quantity, Integer productId, String productName) {
        this.id = id;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
    }

    public RecipeProductDto(RecipeProduct recipeProduct) {
        this.id = recipeProduct.getId();
        this.unitOfMeasure = recipeProduct.getUnitOfMeasure();
        this.quantity = recipeProduct.getQuantity();
        this.productId = recipeProduct.getProductBase().getProductId();
        this.productName = recipeProduct.getProductBase().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
