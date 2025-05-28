package com.prototype.restaurante_el_negro.dto;

import com.prototype.restaurante_el_negro.enums.OrderStatus;
import com.prototype.restaurante_el_negro.models.Order;
import com.prototype.restaurante_el_negro.models.OrderDetails;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResponseOrderDto {
    private Integer id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDIENTE;
    private Date creationDate = Date.valueOf(LocalDate.now());
    private String table;
    private Integer total;
    private List<Integer> details = new ArrayList<>();
    public ResponseOrderDto() {
    }

    public ResponseOrderDto(Integer id, OrderStatus status, Date creationDate, String table, Integer total, List<Integer> details) {
        this.id = id;
        this.status = status;
        this.creationDate = creationDate;
        this.table = table;
        this.total = total;
        this.details = details;
    }

    public ResponseOrderDto(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.creationDate = order.getCreationDate();
        this.total = order.getTotal();
        this.table = order.getTable().getName();
        this.details = order.getDetails().stream().map(OrderDetails::getId).toList();
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Integer> getDetails() {
        return details;
    }

    public void setDetails(List<Integer> details) {
        this.details = details;
    }
}
