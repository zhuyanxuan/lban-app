package com.app.lban.service.impl;

import com.app.lban.dao.ProductDao;
import com.app.lban.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/22 0022.
 */
@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public void addProduct(Product product){
        productDao.addProduct(product);
    }
    public List<Product> selectAllProduct(Product product){
        return productDao.selectAllProduct(product);
    }
    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }
    public void deleteProduct(Product product){
        productDao.deleteProduct(product);
    }

}
