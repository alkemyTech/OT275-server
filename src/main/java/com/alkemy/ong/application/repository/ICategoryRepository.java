package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;
import java.util.List;

public interface ICategoryRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  Category get(Identifiable<Long> identifiable);

  Category update(Category category);

  List<Category> findAll();

  Category save(Category category);

}
