package com.example.FamilyCareInventory.dao;

import com.example.FamilyCareInventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public class ProductJdbcTemp {
    private static final String SELECT_PRODUCT_BY_ID_SQL =
            "SELECT * FROM Product WHERE productId = ?";

    private static final String SELECT_ALL_PRODUCT_SQL =
            "SELECT * FROM Product ORDER BY created_time DESC";

    private static final String SELECT_LAST_INSERT_PRODUCT_ID =
            "SELECT LAST_INSERT_ID() AS productId FROM Product LIMIT 1;";

    private static final String INSERT_PRODUCT_SQL =
            "INSERT INTO Product(product_name, product_version, inventory, created_time) VALUES (?, ?, ?, ?)";

    private static final String DELETE_PRODUCT_SQL =
            "DELETE FROM Product WHERE productId = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Product mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product(rs.getInt("productId"), rs.getString("product_name"),
                rs.getString("product_version"), rs.getInt("inventory"), rs.getDate("created_time"));
        return product;
    }

    public Product getProduct(int productId) {
        Product product = jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID_SQL, this::mapRowToUser, productId);
        return product;
    }

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_PRODUCT_SQL);
        while (rs.next()) {
            Product product = new Product(rs.getInt("productId"), rs.getString("product_name"),
                    rs.getString("product_version"), rs.getInt("inventory"), rs.getDate("created_time"));
            productList.add(product);
        }
        return productList;
    }

    public int listProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT_SQL,
                product.getProduct_name(),
                product.getProduct_version(),
                product.getInventory(),
                LocalDateTime.now()
        );
        int productID = 0;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_LAST_INSERT_PRODUCT_ID);
        if (rs.next()) {
            productID = rs.getInt("productId");
        }
        return productID;
    }

    public void deleteProduct(int productId) {
        jdbcTemplate.update(DELETE_PRODUCT_SQL, productId);
    }
}

