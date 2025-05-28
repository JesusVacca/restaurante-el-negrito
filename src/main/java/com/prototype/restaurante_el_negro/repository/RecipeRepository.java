package com.prototype.restaurante_el_negro.repository;

import com.prototype.restaurante_el_negro.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
