package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryUseCaseServiceTest {

  private DeleteCategoryUseCaseService categoryService;
  @Mock
  private ICategoryRepository categoryRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    categoryService = new DeleteCategoryUseCaseService(categoryRepository);
  }

  @Test
  void shouldThrowExceptionWhenCategoryDoesNotExist() {
    given(categoryRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFound.class, () -> categoryService.delete(identifiable));
  }

  @Test
  void shouldDeleteCategoryWhenCategoryExist() {
    given(categoryRepository.exists(identifiable)).willReturn(true);

    categoryService.delete(identifiable);

    verify(categoryRepository).exists(identifiable);
    verify(categoryRepository).delete(identifiable);
  }
}
