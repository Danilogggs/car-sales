package com.carsales.catalog.controller;

import com.carsales.catalog.domain.Product;
import com.carsales.catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService service;

  public ProductController(ProductService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<Product> create(@Valid @RequestBody Product body) {
    Product saved = service.create(body);
    return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
  }

  @GetMapping
  public List<Product> list() { return service.list(); }

  @GetMapping("/{id}")
  public Product get(@PathVariable String id) { return service.get(id); }

  @GetMapping("/by-category/{categoryId}")
  public List<Product> byCategory(@PathVariable String categoryId) {
    return service.byCategory(categoryId);
  }

  @PatchMapping("/{id}")
  public Product patch(@PathVariable String id, @RequestBody Product changes) {
    return service.update(id, changes);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
