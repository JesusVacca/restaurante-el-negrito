package com.prototype.restaurante_el_negro.models;

import com.prototype.restaurante_el_negro.dto.RecipeDto;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer recipeId;
    private String name;
    private Date creationDate = Date.valueOf(LocalDate.now());
    private boolean isActive = true;
    private Integer price;

    @ManyToMany(mappedBy = "recipes")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeProduct> recipeProducts = new ArrayList<>();

    public Recipe() {}

    public Recipe(Integer recipeId, String name, Date creationDate, boolean isActive, Integer price, List<Menu> menus, List<RecipeProduct> recipeProducts) {
        this.recipeId = recipeId;
        this.name = name;
        this.creationDate = creationDate;
        this.isActive = isActive;
        this.price = price;
        this.menus = menus;
        this.recipeProducts = recipeProducts;
    }

    public Recipe(RecipeDto recipeDto) {
        this.name = recipeDto.getName();
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

    public List<RecipeProduct> getRecipeProducts() {
        return recipeProducts;
    }

    public void setRecipeProducts(List<RecipeProduct> recipeProducts) {
        this.recipeProducts = recipeProducts;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void addRecipeProduct(RecipeProduct recipeProduct) {
        if(!this.recipeProducts.contains(recipeProduct)) {
            this.recipeProducts.add(recipeProduct);
        }
    }
}
