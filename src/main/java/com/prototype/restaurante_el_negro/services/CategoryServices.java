package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.dto.CategoryDto;
import com.prototype.restaurante_el_negro.models.Category;
import com.prototype.restaurante_el_negro.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        this.categoryRepository.save(category);
        return new CategoryDto(category);
    }
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return this.categoryRepository.findAll()
                .stream().map(CategoryDto::new).collect(Collectors.toList());

    }
}
