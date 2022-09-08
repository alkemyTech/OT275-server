package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Slide;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateSlideUseCaseServiceTest {

  private UpdateSlideUseCaseService updateSlideUseCaseService;

  @Mock
  private ISlideRepository slideRepository;

  @Mock
  private Slide slide;

  @BeforeEach
  void setUp() {
    updateSlideUseCaseService = new UpdateSlideUseCaseService(slideRepository);
  }

  @Test
  void shouldUpdateSlide() {
    given(slideRepository.getBy(any())).willReturn(Optional.of(slide));

    updateSlideUseCaseService.update(slide);

    verify(slideRepository, times(1)).update(slide);
  }

  @Test
  void shouldThrowExceptionWhenUpdateSlideWithWrongId() {
    given(slideRepository.getBy(any())).willReturn(Optional.empty());

    assertThrows(ObjectNotFoundException.class, () -> updateSlideUseCaseService.update(slide));

    verify(slideRepository, times(0)).update(slide);
  }
}