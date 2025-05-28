package com.prototype.restaurante_el_negro.dto;
import com.prototype.restaurante_el_negro.models.Recipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RecipeDto {
    private Integer recipeId;
    private String name;
    private Date creationDate;
    private boolean isActive = true;
    private Integer price;
    private List<RecipeProductDto> recipeProductsDto = new ArrayList<>();

    public RecipeDto() {
    }

    public RecipeDto(Integer recipeId, String name, Date creationDate, boolean isActive, Integer price, List<RecipeProductDto> recipeProductsDto) {
        this.recipeId = recipeId;
        this.name = name;
        this.creationDate = creationDate;
        this.isActive = isActive;
        this.price = price;
        this.recipeProductsDto = recipeProductsDto;
    }

    public RecipeDto(Recipe recipe) {
        this.recipeId = recipe.getRecipeId();
        this.name = recipe.getName();
        this.creationDate = recipe.getCreationDate();
        this.isActive = recipe.isActive();
        this.price = recipe.getPrice();
        if(recipe.getRecipeProducts() != null || recipe.getRecipeProducts().isEmpty()) {
            this.recipeProductsDto = recipe.getRecipeProducts()
                    .stream()
                    .map(RecipeProductDto::new)
                    .toList();
        }
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<RecipeProductDto> getRecipeProductsDto() {
        return recipeProductsDto;
    }

    public void setRecipeProductsDto(List<RecipeProductDto> recipeProductsDto) {
        this.recipeProductsDto = recipeProductsDto;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
