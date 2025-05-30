package com.prototype.restaurante_el_negro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
