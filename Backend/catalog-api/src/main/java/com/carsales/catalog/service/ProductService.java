package com.carsales.catalog.service;

import com.carsales.catalog.domain.Product;
import com.carsales.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductService {
  private final ProductRepository repo;

  public ProductService(ProductRepository repo) { this.repo = repo; }

  public Product create(Product p) {
    p.setId(null);
    p.setCreatedAt(Instant.now());
    p.setUpdatedAt(Instant.now());
    return repo.save(p);
  }

  public List<Product> list() {
    return repo.findAll();
  }

  public Product get(String id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
  }

  public List<Product> byCategory(String categoryId) {
    return repo.findByCategoryId(categoryId);
  }

  public Product update(String id, Product changes) {
    Product p = get(id);
    if (changes.getCategoryId() != null) p.setCategoryId(changes.getCategoryId());
    if (changes.getBrand() != null) p.setBrand(changes.getBrand());
    if (changes.getModel() != null) p.setModel(changes.getModel());
    if (changes.getVersion() != null) p.setVersion(changes.getVersion());
    if (changes.getYear() != null) p.setYear(changes.getYear());
    if (changes.getColor() != null) p.setColor(changes.getColor());
    if (changes.getListedPrice() != null) p.setListedPrice(changes.getListedPrice());
    if (changes.getPhotos() != null) p.setPhotos(changes.getPhotos());
    if (changes.getStatus() != null) p.setStatus(changes.getStatus());
    p.setUpdatedAt(Instant.now());
    return repo.save(p);
  }

  public void delete(String id) {
    repo.deleteById(id);
  }
}
