package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableModelRepository extends JpaRepository<TableModel, Integer> {
}
