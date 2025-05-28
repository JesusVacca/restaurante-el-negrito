package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBaseRepository extends JpaRepository<ProductBase, Integer> {
}
