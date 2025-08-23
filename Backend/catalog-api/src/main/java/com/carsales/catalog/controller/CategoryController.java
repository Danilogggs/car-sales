package com.carsales.catalog.controller;

import com.carsales.catalog.domain.Category;
import com.carsales.catalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService service;

  public CategoryController(CategoryService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<Category> create(@Valid @RequestBody Category body) {
    Category saved = service.create(body);
    return ResponseEntity.created(URI.create("/api/categories/" + saved.getId())).body(saved);
  }

  @GetMapping
  public List<Category> list() { return service.list(); }

  @GetMapping("/{id}")
  public Category get(@PathVariable String id) { return service.get(id); }

  @PatchMapping("/{id}")
  public Category patch(@PathVariable String id, @RequestBody Category changes) {
    return service.update(id, changes);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
