package com.prototype.restaurante_el_negro.models;

import com.prototype.restaurante_el_negro.dto.ProductBaseDto;
import com.prototype.restaurante_el_negro.enums.ProductStatus;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_base")
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    private String name;
    private String description;
    private String unitOfMeasure;
    private Integer quantity;
    private Integer price;
    private Date creationDate = Date.valueOf(LocalDate.now());
    private Date modificationDate = Date.valueOf(LocalDate.now());
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.AVAILABLE;

    @OneToOne(mappedBy = "productBase", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductImage productImage;

    @ManyToOne
    private Category category;
    public ProductBase() {}

    @OneToMany
    private List<RecipeProduct> recipeProductList = new ArrayList<>();

    public ProductBase(Integer productId, String name, String description, String unitOfMeasure, Integer quantity, Integer price, Date creationDate, Date modificationDate, ProductStatus status, ProductImage productImage, Category category, List<RecipeProduct> recipeProductList) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
        this.price = price;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.status = status;
        this.productImage = productImage;
        this.category = category;
        this.recipeProductList = recipeProductList;
    }

    public ProductBase(ProductBaseDto productBaseDto) {
        this.name = productBaseDto.getName();
        this.description = productBaseDto.getDescription();
        this.unitOfMeasure = productBaseDto.getUnitOfMeasure();
        this.quantity = productBaseDto.getQuantity();
        this.price = productBaseDto.getPrice();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<RecipeProduct> getRecipeProductList() {
        return recipeProductList;
    }

    public void setRecipeProductList(List<RecipeProduct> recipeProductList) {
        this.recipeProductList = recipeProductList;
    }

    public void updateData(ProductBaseDto productBaseDto) {
        this.name = productBaseDto.getName();
        this.description = productBaseDto.getDescription();
        this.unitOfMeasure = productBaseDto.getUnitOfMeasure();
        this.quantity = productBaseDto.getQuantity();
        this.price = productBaseDto.getPrice();
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    public void addRecipeProduct(RecipeProduct recipeProduct) {
        if(!this.recipeProductList.contains(recipeProduct)) {
            this.recipeProductList.add(recipeProduct);
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
