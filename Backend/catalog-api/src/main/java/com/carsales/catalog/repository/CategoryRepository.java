package com.carsales.catalog.repository;

import com.carsales.catalog.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
  Optional<Category> findBySlug(String slug);
}
