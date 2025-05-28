package com.prototype.restaurante_el_negro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    private String id;
    private String urlImage;
    @OneToOne
    private ProductBase productBase;

    public ProductImage() {}
    public ProductImage(String id, String urlImage, ProductBase productBase) {
        this.id = id;
        this.urlImage = urlImage;
        this.productBase = productBase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ProductBase getProductBase() {
        return productBase;
    }

    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }
}
