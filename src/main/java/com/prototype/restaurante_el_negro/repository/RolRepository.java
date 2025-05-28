package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.enums.RolEnum;
import com.prototype.restaurante_el_negro.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, RolEnum> {
}
