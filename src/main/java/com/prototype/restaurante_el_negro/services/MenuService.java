package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.dto.MenuDto;
import com.prototype.restaurante_el_negro.dto.RecipeDto;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.Menu;
import com.prototype.restaurante_el_negro.models.Recipe;
import com.prototype.restaurante_el_negro.repository.MenuRepository;
import com.prototype.restaurante_el_negro.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RecipeRepository recipeRepository;
    public MenuService(MenuRepository menuRepository, RecipeRepository recipeRepository) {
        this.menuRepository = menuRepository;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public MenuDto createMenu(MenuDto menuDto) {
        this.menuRepository.disableMenuActive();
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        for (RecipeDto recipeDto : menuDto.getRecipes()) {
            Recipe recipe = this.recipeRepository.findById(recipeDto.getRecipeId())
                    .orElseThrow(()-> new NotFoundException("Recipe not found"));
            menu.getRecipes().add(recipe);
            recipe.getMenus().add(menu);
            System.out.println(recipe.getPrice());
        }
        this.menuRepository.save(menu);
        return new MenuDto(menu);
    }

    public List<MenuDto> getAllMenu() {
        return this.menuRepository.findAll().stream().map(MenuDto::new).collect(Collectors.toList());
    }

    public List<MenuDto> getMenuActive() {
        return this.menuRepository.findAll().stream().map(MenuDto::new).filter(MenuDto::isActive).toList();
    }
}
