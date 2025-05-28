package com.prototype.restaurante_el_negro.dto;

public class DetailDto {
    private Integer recipe_id;
    private Integer quantity;

    public DetailDto() {
    }

    public DetailDto(Integer recipe_id, Integer quantity) {
        this.recipe_id = recipe_id;
        this.quantity = quantity;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
