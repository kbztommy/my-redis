package com.kbz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kbz.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {}
