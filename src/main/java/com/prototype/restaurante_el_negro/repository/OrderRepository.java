package com.prototype.restaurante_el_negro.repository;


import com.prototype.restaurante_el_negro.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
}
