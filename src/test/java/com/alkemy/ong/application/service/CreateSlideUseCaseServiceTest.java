package com.alkemy.ong.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.util.IImageUploader;
import com.alkemy.ong.common.IntegerMother;
import com.alkemy.ong.common.SlideMother;
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
    Slide expected = SlideMother.random();

    createSlideUseCaseService.add(expected);

    verify(slideRepository, times(1)).add(expected);
  }

  @Test
  void shouldSetNextPositionWhenOrderIsNull() {
    Slide slideWithNullOrder = SlideMother.withNullOrder();
    Integer random = IntegerMother.random();
    Integer expected = random + 1;

    given(slideRepository.findMaxPosition()).willReturn(random);

    createSlideUseCaseService.add(slideWithNullOrder);

    assertThat(slideWithNullOrder.getOrder()).isEqualTo(expected);
  }

}
