package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.dto.RecipeDto;
import com.prototype.restaurante_el_negro.dto.RecipeProductDto;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.ProductBase;
import com.prototype.restaurante_el_negro.models.Recipe;
import com.prototype.restaurante_el_negro.models.RecipeProduct;
import com.prototype.restaurante_el_negro.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServices {
    private final RecipeRepository recipeRepository;
    private final ProductBaseServices productBaseServices;
    public RecipeServices(RecipeRepository recipeRepository, ProductBaseServices productBaseServices) {
        this.recipeRepository = recipeRepository;
        this.productBaseServices = productBaseServices;
    }

    @Transactional
    public RecipeDto createRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe(recipeDto);
        recipe.setPrice(recipeDto.getPrice());
        if(recipeDto.getRecipeProductsDto() == null || recipeDto.getRecipeProductsDto().isEmpty()) {
            throw new IllegalArgumentException("PARA CREAR UNA RECETA MINIMO DEBE HABER UN PRODUCTO");
        }
        for(RecipeProductDto recipeProductDto : recipeDto.getRecipeProductsDto()){
            ProductBase productBase = this.productBaseServices
                    .getProductBaseById(recipeProductDto.getProductId());
            if(recipeProductDto.getQuantity() > productBase.getQuantity()){
                throw new IllegalArgumentException("NO HAY PRDUCTO SUFICIENTE DE "+productBase.getName()+" EN EL INVENTARIO");
            }
            productBase.setQuantity(productBase.getQuantity() - recipeProductDto.getQuantity());
            RecipeProduct recipeProduct = new RecipeProduct(recipeProductDto);
            recipeProduct.setUnitOfMeasure(productBase.getUnitOfMeasure());
            recipeProduct.setProductBase(productBase);
            recipeProduct.setRecipe(recipe);
            productBase.addRecipeProduct(recipeProduct);
            recipe.addRecipeProduct(recipeProduct);
        }
        this.recipeRepository.save(recipe);
        return new RecipeDto(recipe);
    }
    @Transactional(readOnly = true)
    public List<RecipeDto> getAllRecipes(){
        return this.recipeRepository.findAll()
                .stream()
                .map(RecipeDto::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public RecipeDto getRecipe(Integer recipeId) {
        Recipe recipe =  this.recipeRepository.findById(recipeId)
                .orElseThrow(()-> new NotFoundException("RECETA NO ENCONTRADA"));
        return new RecipeDto(recipe);
    }
}
