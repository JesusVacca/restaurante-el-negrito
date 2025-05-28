package com.prototype.restaurante_el_negro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "table_model")
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean available = true;
    @OneToMany
    @JsonIgnore
    private List<Order> Order = new ArrayList<Order>();
    public TableModel() {
    }
    public TableModel(Integer id, String name, boolean available, List<Order> Order) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.Order = Order;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setOrder(List<Order> order) {
        this.Order = order;
    }

    public List<Order> getOrder() {
        return Order;
    }
}
