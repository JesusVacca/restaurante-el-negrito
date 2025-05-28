package com.prototype.restaurante_el_negro;

import com.prototype.restaurante_el_negro.enums.InitCategories;
import com.prototype.restaurante_el_negro.enums.RolEnum;
import com.prototype.restaurante_el_negro.models.Category;
import com.prototype.restaurante_el_negro.models.Rol;
import com.prototype.restaurante_el_negro.repository.CategoryRepository;
import com.prototype.restaurante_el_negro.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class RestauranteElNegroApplication implements CommandLineRunner {
	private final CategoryRepository categoryRepository;
	private final RolRepository rolRepository;
	public RestauranteElNegroApplication(CategoryRepository categoryRepository, RolRepository rolRepository) {
		this.categoryRepository = categoryRepository;
        this.rolRepository = rolRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(RestauranteElNegroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(InitCategories initCategory : InitCategories.values()) {
			Category category = new Category();
			category.setName(initCategory.name());
			if(!this.categoryRepository.existsById(category.getName())){
				this.categoryRepository.save(category);
			}
		}
		for (RolEnum rolEnum : RolEnum.values()) {
			if(!this.rolRepository.existsById(rolEnum)){
				this.rolRepository.save(new Rol(rolEnum, new ArrayList<>()));
			}
		}
	}
}
