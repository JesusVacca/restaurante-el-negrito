package com.prototype.restaurante_el_negro.dto;

import com.prototype.restaurante_el_negro.models.Menu;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private Integer menuId;
    private String name;
    private Date creationDate;
    private boolean isActive;
    private List<RecipeDto> recipes = new ArrayList<>();

    public MenuDto() {
    }

    public MenuDto(Integer menuId, String name ,Date creationDate, List<RecipeDto> recipes) {
        this.menuId = menuId;
        this.name = name;
        this.creationDate = creationDate;
        this.recipes = recipes;
    }

    public MenuDto(Menu menu) {
        this.menuId = menu.getMenuId();
        this.name = menu.getName();
        this.creationDate = menu.getCreationDate();
        this.isActive = menu.isActive();
        this.recipes = menu.getRecipes() != null?
                menu.getRecipes()
                        .stream()
                        .map(RecipeDto::new)
                        .toList()
                : new ArrayList<RecipeDto>();
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
