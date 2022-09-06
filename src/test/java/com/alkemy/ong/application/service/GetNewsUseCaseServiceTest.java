package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.common.NewsBuilder;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetNewsUseCaseServiceTest {

  private GetNewsUseCaseService getNewsUseCaseService;

  @Mock
  private INewsRepository newsRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setUp() {
    getNewsUseCaseService = new GetNewsUseCaseService(newsRepository);
  }

  @Test
  void shouldThrowExceptionWhenNewsDoesNotExist() {
    given(newsRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class, () -> getNewsUseCaseService.get(identifiable));
    verify(newsRepository, times(0)).get(identifiable);
  }

  @Test
  void shouldGetNews() {
    News news = NewsBuilder.random();
    given(newsRepository.get(identifiable)).willReturn(news);
    given(newsRepository.exists(identifiable)).willReturn(true);

    getNewsUseCaseService.get(identifiable);

    verify(newsRepository, times(1)).get(identifiable);
  }

}
