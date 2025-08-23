package com.carsales.catalog.service;

import com.carsales.catalog.domain.Category;
import com.carsales.catalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CategoryService {
  private final CategoryRepository repo;

  public CategoryService(CategoryRepository repo) { this.repo = repo; }

  public Category create(Category c) {
    c.setId(null);
    c.setCreatedAt(Instant.now());
    c.setUpdatedAt(Instant.now());
    return repo.save(c);
  }

  public List<Category> list() {
    return repo.findAll();
  }

  public Category get(String id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
  }

  public Category update(String id, Category changes) {
    Category c = get(id);
    if (changes.getName() != null) c.setName(changes.getName());
    if (changes.getSlug() != null) c.setSlug(changes.getSlug());
    c.setUpdatedAt(Instant.now());
    return repo.save(c);
  }

  public void delete(String id) {
    repo.deleteById(id);
  }
}
