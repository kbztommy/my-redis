package com.kbz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kbz.domain.Product;
import com.kbz.repository.ProductRepository;
import com.kbz.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired ProductRepository productRepository;

  @Override
  @CachePut(cacheNames = "product", key = "#product.id")
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  @Cacheable(cacheNames = "product")
  public Product findById(String id) {
    return productRepository.findById(id).get();
  }

  @Override
  @CacheEvict(cacheNames = "product", key = "#product.id")
  public void delete(Product product) {
    productRepository.delete(product);
    throw new RuntimeException();
  }
}
