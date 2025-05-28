package com.prototype.restaurante_el_negro.services;

import com.prototype.restaurante_el_negro.dto.ProductBaseDto;
import com.prototype.restaurante_el_negro.enums.ProductStatus;
import com.prototype.restaurante_el_negro.exceptions.NotFoundException;
import com.prototype.restaurante_el_negro.models.Category;
import com.prototype.restaurante_el_negro.models.ProductBase;
import com.prototype.restaurante_el_negro.models.ProductImage;
import com.prototype.restaurante_el_negro.repository.CategoryRepository;
import com.prototype.restaurante_el_negro.repository.ProductBaseRepository;
import com.prototype.restaurante_el_negro.repository.ProductImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductBaseServices {
    private final ProductBaseRepository productBaseRepository;
    private final CloudinaryServices cloudinaryServices;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    public ProductBaseServices(ProductBaseRepository productBaseRepository, CloudinaryServices cloudinaryServices, ProductImageRepository productImageRepository, CategoryRepository categoryRepository) {
        this.productBaseRepository = productBaseRepository;
        this.cloudinaryServices = cloudinaryServices;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductBaseDto createProductBase(ProductBaseDto productBaseDto, MultipartFile imageFile) throws IOException {
        ProductBase productBase = new ProductBase(productBaseDto);
        ProductImage productImage = new ProductImage();
        if(productBaseDto.getQuantity() == null || productBaseDto.getQuantity() <= 0){
            throw new IllegalArgumentException("PARA AGREGAR UN PRODUCTO LA CANTIDAD DEBE SER MAYOR A CERO(0)");
        }
        Map<?,?> cloudinaryResponse = this.cloudinaryServices.uploadImage(imageFile);
        if (cloudinaryResponse != null) {
            productImage.setUrlImage(String.valueOf(cloudinaryResponse.get("secure_url")));
            productImage.setId(String.valueOf(cloudinaryResponse.get("public_id")));
            productImage.setProductBase(productBase);
            productBase.setProductImage(productImage);
        }
        Category category = this.categoryRepository.findById(productBaseDto.getCategory())
                        .orElseThrow(() -> new NotFoundException("NO EXISTE ESA CATEGORIA"));
        productBase.setCategory(category);
        this.productBaseRepository.save(productBase);
        return new ProductBaseDto(productBase);
    }

    @Transactional(readOnly = true)
    public List<ProductBaseDto> getAllProductBases() {
        return this.productBaseRepository.findAll().stream().map(ProductBaseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Page<ProductBase> getAllProductBasesDto(Pageable pageable) {
        return this.productBaseRepository.findAll(
                pageable
        );
    }
    @Transactional(readOnly = true)
    public ProductBaseDto getProductBaseDtoById(Integer id) {
        ProductBase productBase = this.getProductBaseById(id);
        return new ProductBaseDto(productBase);
    }

    @Transactional(readOnly = true)
    protected ProductBase getProductBaseById(Integer id){
        return this.productBaseRepository
                .findById(id)
                .orElseThrow(()-> new NotFoundException("PRODUCTO NO ENCONTRADO"));
    }

    @Transactional
    public ProductBaseDto updateProductBase(ProductBaseDto productBaseDto, MultipartFile imageFile) throws Exception {
        ProductBase productBase = this.getProductBaseById(productBaseDto.getId());
        productBase.updateData(productBaseDto);
        if(imageFile != null){
            Map<?,?> cloudinaryResponse = this.cloudinaryServices.uploadImage(imageFile);
            if (cloudinaryResponse != null) {
                if(productBase.getProductImage() != null){
                    this.productImageRepository.delete(productBase.getProductImage());
                    this.cloudinaryServices.deleteImage(productBase.getProductImage().getId());
                }
                productBase.setProductImage(new ProductImage(
                        String.valueOf(cloudinaryResponse.get("secure_url")),
                        String.valueOf(cloudinaryResponse.get("public_id")),
                        productBase
                ));
            }
        }
        return new ProductBaseDto(productBase);
    }
    @Transactional
    public ProductBaseDto deleteProductBase(Integer id) {
        ProductBase productBase = this.getProductBaseById(id);
        productBase.setStatus(ProductStatus.DELETED);
        this.productBaseRepository.save(productBase);
        return new ProductBaseDto(productBase);
    }
}
