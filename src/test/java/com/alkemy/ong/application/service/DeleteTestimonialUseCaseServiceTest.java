package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteTestimonialUseCaseServiceTest {

  private DeleteTestimonialUseCaseService deleteTestimonialUseCaseService;

  @Mock
  private ITestimonialRepository testimonialRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    deleteTestimonialUseCaseService = new DeleteTestimonialUseCaseService(testimonialRepository);
  }

  @Test
  void shouldThrowExceptionWhenTestimonialDoesNotExist() {
    given(testimonialRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> deleteTestimonialUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteTestimonialWhenTestimonialExist() {
    given(testimonialRepository.exists(identifiable)).willReturn(true);

    deleteTestimonialUseCaseService.delete(identifiable);

    verify(testimonialRepository).exists(identifiable);
    verify(testimonialRepository).delete(identifiable);
  }

}
