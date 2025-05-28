package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.dto.RecipeDto;
import com.prototype.restaurante_el_negro.enums.InitCategories;
import com.prototype.restaurante_el_negro.services.ProductBaseServices;
import com.prototype.restaurante_el_negro.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("dashboard/recipes/")
public class RecipeController {

    private final RecipeServices recipeServices;
    private final ProductBaseServices productBaseServices;
    public RecipeController(RecipeServices recipeServices, ProductBaseServices productBaseServices) {
        this.recipeServices = recipeServices;
        this.productBaseServices = productBaseServices;
    }

    @GetMapping
    public String recipeAll(Model model) {
        List<RecipeDto> recipes = this.recipeServices.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes/listRecipe";
    }
    @GetMapping("create-recipe")
    public String createRecipe(Model model) {
        model.addAttribute("products",
                this.productBaseServices
                        .getAllProductBases()
                        .stream()
                        .filter(product -> !Objects.equals(product.getCategory(), InitCategories.BEBIDAS.name()))
        );
        return "recipes/create";
    }
    @PostMapping("create-recipe")
    public ResponseEntity<Object> createRecipe(@RequestBody RecipeDto recipeDto){
        try {
            RecipeDto res = this.recipeServices.createRecipe(recipeDto);
            return ResponseEntity
                    .status(201)
                    .body(res);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }

}
