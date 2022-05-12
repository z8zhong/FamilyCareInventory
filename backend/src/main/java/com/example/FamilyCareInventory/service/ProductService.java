package com.example.FamilyCareInventory.service;

import com.example.FamilyCareInventory.dao.ProductJdbcTemp;
import com.example.FamilyCareInventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductJdbcTemp productJdbcTemp;

    @Autowired
    public ProductService(ProductJdbcTemp productJdbcTemp) {
        this.productJdbcTemp = productJdbcTemp;
    }

    public Product getProduct(int productId) {
        return productJdbcTemp.getProduct(productId);
    }

    public List<Product> getAllProduct() {
        return productJdbcTemp.getAllProduct();
    }

    public int listProduct(Product product) {
        return productJdbcTemp.listProduct(product);
    }

    public void deleteProduct(int productId) {productJdbcTemp.deleteProduct(productId);}
}
