package com.alkemy.ong.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.util.IImageUploader;
import com.alkemy.ong.common.SlideBuilder;
import com.alkemy.ong.domain.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSlideUseCaseServiceTest {

  private CreateSlideUseCaseService createSlideUseCaseService;

  @Mock
  private ISlideRepository slideRepository;

  @Mock
  private IImageUploader imageUploader;

  @BeforeEach
  void setUp() {
    createSlideUseCaseService = new CreateSlideUseCaseService(slideRepository, imageUploader);
  }

  @Test
  void shouldAddSlide() {
    Slide expected = SlideBuilder.random();

    createSlideUseCaseService.add(expected);

    verify(slideRepository, times(1)).add(expected);
    verify(slideRepository, times(0)).findMaxPosition();
  }

  @Test
  void shouldSetNextPositionWhenOrderIsNull() {
    Slide slideWithNullOrder = SlideBuilder.withNullOrder();
    given(slideRepository.findMaxPosition()).willReturn(2);
    given(slideRepository.add(slideWithNullOrder)).willReturn(slideWithNullOrder);

    Slide savedSlide = createSlideUseCaseService.add(slideWithNullOrder);

    assertThat(savedSlide.getOrder()).isEqualTo(3);
    verify(slideRepository, times(1)).add(slideWithNullOrder);
  }

}
