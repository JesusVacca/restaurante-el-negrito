package com.prototype.restaurante_el_negro.dto;

import com.prototype.restaurante_el_negro.models.Category;

public class CategoryDto {
    private String name;

    public CategoryDto() {}

    public CategoryDto(String name) {
        this.name = name;
    }
    public CategoryDto(Category category) {
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
