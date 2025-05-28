package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, String> {

}
