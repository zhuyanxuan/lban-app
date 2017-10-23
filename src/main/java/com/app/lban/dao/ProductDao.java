package com.app.lban.dao;

import com.app.lban.model.Product;
import com.app.lban.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    public void addProduct(Product product);
    public List<Product> selectAllProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(Product product);
}
