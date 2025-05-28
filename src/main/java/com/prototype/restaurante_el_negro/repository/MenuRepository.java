package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Modifying
    @Query("UPDATE Menu m SET m.isActive = false")
    void disableMenuActive();
    @Modifying
    @Query("SELECT m FROM Menu m ORDER BY(m.isActive) DESC")
    List<Menu> findAll();
}
