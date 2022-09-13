package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.slide.GetSlideUseCaseService;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Slide;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSlideUseCaseServiceTest {

  private GetSlideUseCaseService getSlideUseCaseService;

  @Mock
  private ISlideRepository slideRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @Mock
  private Slide slide;

  @BeforeEach
  void setup() {
    getSlideUseCaseService = new GetSlideUseCaseService(slideRepository);
  }

  @Test
  void shouldThrowExceptionWhenSlideDoesNotExist() {
    given(slideRepository.getBy(identifiable)).willReturn(Optional.empty());

    assertThrows(ObjectNotFoundException.class, () -> getSlideUseCaseService.getBy(identifiable));
  }

  @Test
  void shouldGetSlideById() {
    given(slideRepository.getBy(identifiable)).willReturn(Optional.of(slide));

    getSlideUseCaseService.getBy(identifiable);

    verify(slideRepository).getBy(identifiable);
  }

}
