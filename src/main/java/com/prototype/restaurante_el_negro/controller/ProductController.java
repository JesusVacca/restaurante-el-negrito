package com.prototype.restaurante_el_negro.controller;

import com.prototype.restaurante_el_negro.dto.CategoryDto;
import com.prototype.restaurante_el_negro.dto.ProductBaseDto;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.services.CategoryServices;
import com.prototype.restaurante_el_negro.services.ProductBaseServices;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("dashboard/products/")
public class ProductController {

    private final ProductBaseServices productBaseServices;
    private final CategoryServices categoryServices;
    public ProductController(ProductBaseServices productBaseServices, CategoryServices categoryServices) {
        this.productBaseServices = productBaseServices;
        this.categoryServices = categoryServices;
    }

    @GetMapping
    public String findAllProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC,"name"));
        List<ProductBaseDto> products = this.productBaseServices.getAllProductBasesDto(pageable)
                        .getContent()
                        .stream()
                        .map(ProductBaseDto::new)
                        .toList();
        model.addAttribute("products",products);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("hasPrevious", pageable.hasPrevious());
        return "products/listProducts";
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> findProductById(@PathVariable Integer id) {
        try{
            ProductBaseDto response = this.productBaseServices.getProductBaseDtoById(id);
            return ResponseEntity.ok(response);
        }catch (NotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @GetMapping("create-product")
    public String createProduct(Model model) {
        model.addAttribute("categories", this.categoryServices.findAll());
        return "products/create";
    }
    @PostMapping("create-product")
    public ResponseEntity<Object> createProduct(@RequestParam(name = "product") ProductBaseDto productBaseDto,@RequestParam("image") MultipartFile image) {
        try{
            ProductBaseDto response = this.productBaseServices.createProductBase(productBaseDto,image);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }
}
