package com.prototype.restaurante_el_negro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prototype.restaurante_el_negro.enums.OrderStatus;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDIENTE;
    private Date creationDate = Date.valueOf(LocalDate.now());
    @ManyToOne
    @JsonIgnore
    private TableModel table;
    private Integer total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetails> details = new ArrayList<>();

    public Order() {
    }

    public Order(Integer id, OrderStatus status, Date creationDate, TableModel table, Integer total, List<OrderDetails> details) {
        this.id = id;
        this.status = status;
        this.creationDate = creationDate;
        this.table = table;
        this.total = total;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
        this.table = table;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OrderDetails> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetails> details) {
        this.details = details;
    }
}
