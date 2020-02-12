package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws Exception;

    void saveProduct(Product product);

}
