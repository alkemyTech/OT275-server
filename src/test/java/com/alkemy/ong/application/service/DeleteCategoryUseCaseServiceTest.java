package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.category.DeleteCategoryUseCaseService;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryUseCaseServiceTest {

  private DeleteCategoryUseCaseService deleteCategoryUseCaseService;
  @Mock
  private ICategoryRepository categoryRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    deleteCategoryUseCaseService = new DeleteCategoryUseCaseService(categoryRepository);
  }

  @Test
  void shouldThrowExceptionWhenCategoryDoesNotExist() {
    given(categoryRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> deleteCategoryUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteCategoryWhenCategoryExist() {
    given(categoryRepository.exists(identifiable)).willReturn(true);

    deleteCategoryUseCaseService.delete(identifiable);

    verify(categoryRepository).exists(identifiable);
    verify(categoryRepository).delete(identifiable);
  }
}
