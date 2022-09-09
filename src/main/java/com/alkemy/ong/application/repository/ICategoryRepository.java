package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  Category get(Identifiable<Long> identifiable);

  Category update(Category category);

  Page<Category> findAll(Pageable pageable);

  Category save(Category category);

  Category findByNameIgnoreCase(String name);

}