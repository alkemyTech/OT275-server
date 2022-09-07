package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.mapper.CategoryEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ICategorySpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  private final ICategorySpringRepository categorySpringRepository;
  private final CategoryEntityMapper categoryEntityMapper;

  @Override
  public Category update(Category category) {
    CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
    return categoryEntityMapper.toDomain(categorySpringRepository.save(categoryEntity));
  }

  @Override
  public Page<Category> findAll(Pageable pageable) {
    Page<CategoryEntity> categories = categorySpringRepository.findAllBySoftDeletedFalse(pageable);
    return categoryEntityMapper.toPageDomain(
        categories.getContent(),
        categories.getNumber(),
        categories.getSize(),
        categories.getTotalElements());
  }

  @Override
  public void delete(Identifiable<Long> identifiable) {
    categorySpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return categorySpringRepository.exists(identifiable.getId()).isPresent();
  }

  @Override
  public Category save(Category category) {
    CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
    categoryEntity.setSoftDeleted(false);
    return categoryEntityMapper.toDomain(categorySpringRepository.save(categoryEntity));
  }

  @Override
  public Category get(Identifiable<Long> identifiable) {
    return categoryEntityMapper.toDomain(
        categorySpringRepository.findByCategoryIdAndSoftDeletedFalse(identifiable.getId()));
  }
}