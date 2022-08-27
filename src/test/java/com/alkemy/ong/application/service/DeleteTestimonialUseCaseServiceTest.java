package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteTestimonialUseCaseServiceTest {

  private DeleteTestimonialUseCaseService testimonialService;

  @Mock
  private ITestimonialRepository testimonialRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    testimonialService = new DeleteTestimonialUseCaseService(testimonialRepository);
  }

  @Test
  void shouldThrowExceptionWhenTestimonialDoesNotExist() {
    given(testimonialRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFound.class, () -> testimonialService.delete(identifiable));
  }

  @Test
  void shouldDeleteTestimonialWhenTestimonialExist() {
    given(testimonialRepository.exists(identifiable)).willReturn(true);

    testimonialService.delete(identifiable);

    verify(testimonialRepository).exists(identifiable);
    verify(testimonialRepository).delete(identifiable);
  }

}
