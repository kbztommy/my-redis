package com.kbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbz.domain.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "商品api")
public class ProductController {

  @Autowired private RedisTemplate<String, Object> redisTemplate;

  @PostMapping("/product")
  @ApiOperation("新增商品")
  public String createProduct(Product product) {
    redisTemplate.opsForValue().set(product.getId(), product);
    return "新增成功";
  }

  @GetMapping("/product/{id}")
  @ApiOperation("查詢商品")
  public Product getProduct(@PathVariable String id) {
    return (Product) (redisTemplate.opsForValue().get(id));
  }

  @PutMapping("/product/{id}")
  @ApiOperation("修改商品")
  public String updateProduct(Product product) {
    redisTemplate.opsForValue().set(product.getId(), product);
    return "修改成功";
  }

  @DeleteMapping("/product/{id}")
  @ApiOperation("刪除商品")
  public String deleteProduct(@PathVariable String id) {
    redisTemplate.delete(id);
    return "刪除成功";
  }
}
