package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.dto.MenuDto;
import com.prototype.restaurante_el_negro.services.MenuService;
import com.prototype.restaurante_el_negro.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/menu/")
public class MenuController {

    private final RecipeServices recipeServices;
    private final MenuService menuService;

    public MenuController(RecipeServices recipeServices, MenuService menuService) {
        this.recipeServices = recipeServices;
        this.menuService = menuService;
    }

    @GetMapping
    public String menu(Model model){
        model.addAttribute("menus",this.menuService.getAllMenu());
        return "menu/listMenu";
    }

    @GetMapping("/create-menu")
    public String createMenu(Model model){
        model.addAttribute("recipes",this.recipeServices.getAllRecipes() );
        return "menu/create";
    }

    @PostMapping("/create-menu")
    public ResponseEntity<Object> createMenu(@RequestBody MenuDto menuDto){
        try{
            MenuDto newMenu = this.menuService.createMenu(menuDto);
            return ResponseEntity
                    .status(201)
                    .body(newMenu);
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }
}
