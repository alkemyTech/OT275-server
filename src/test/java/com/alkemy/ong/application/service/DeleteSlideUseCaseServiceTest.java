package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.slide.DeleteSlideUseCaseService;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteSlideUseCaseServiceTest {

  private DeleteSlideUseCaseService deleteSlideUseCaseService;
  @Mock
  private ISlideRepository slideRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    deleteSlideUseCaseService = new DeleteSlideUseCaseService(slideRepository);
  }

  @Test
  void shouldThrowExceptionWhenSlideDoesNotExist() {
    given(slideRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> deleteSlideUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteSlideWhenSlideExist() {
    given(slideRepository.exists(identifiable)).willReturn(true);

    deleteSlideUseCaseService.delete(identifiable);

    verify(slideRepository).exists(identifiable);
    verify(slideRepository).delete(identifiable);
  }

}
