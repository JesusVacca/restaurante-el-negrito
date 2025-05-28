package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}
