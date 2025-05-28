package com.prototype.restaurante_el_negro.models;

import com.prototype.restaurante_el_negro.dto.RecipeProductDto;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe_product")
public class RecipeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String unitOfMeasure;
    private Integer quantity;
    @ManyToOne
    private ProductBase productBase;
    @ManyToOne
    private Recipe recipe;

    public RecipeProduct() {
    }

    public RecipeProduct(Integer id, String unitOfMeasure, Integer quantity, ProductBase productBase, Recipe recipe) {
        this.id = id;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
        this.productBase = productBase;
        this.recipe = recipe;
    }

    public RecipeProduct(RecipeProductDto recipeProductDto) {
        this.unitOfMeasure = recipeProductDto.getUnitOfMeasure();
        this.quantity = recipeProductDto.getQuantity();
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

    public ProductBase getProductBase() {
        return productBase;
    }

    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
