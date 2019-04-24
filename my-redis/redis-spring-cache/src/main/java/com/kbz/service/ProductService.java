package com.kbz.service;

import com.kbz.domain.Product;

public interface ProductService {

  Product save(Product product);

  Product findById(String id);

  void delete(Product product);
}
