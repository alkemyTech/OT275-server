package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteSlideUseCaseServiceTest {

  private DeleteSlideUseCaseService slideService;
  @Mock
  private ISlideRepository slideRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    slideService = new DeleteSlideUseCaseService(slideRepository);
  }

  @Test
  void shouldThrowExceptionWhenUserDoesNotExist() {
    given(slideRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFound.class, () -> slideService.delete(identifiable));
  }

  @Test
  void shouldDeleteUserWhenUserExist() {
    given(slideRepository.exists(identifiable)).willReturn(true);

    slideService.delete(identifiable);

    verify(slideRepository).exists(identifiable);
    verify(slideRepository).delete(identifiable);
  }

}
