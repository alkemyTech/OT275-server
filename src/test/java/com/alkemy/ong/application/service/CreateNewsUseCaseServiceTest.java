package com.alkemy.ong.application.service;

import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.news.CreateNewsUseCaseService;
import com.alkemy.ong.builder.NewsBuilder;
import com.alkemy.ong.domain.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateNewsUseCaseServiceTest {

  private CreateNewsUseCaseService createNewsUseCaseService;

  @Mock
  private INewsRepository newsRepository;

  @Mock
  private ICategoryRepository categoryRepository;

  @Mock
  private News news;

  @BeforeEach
  void setUp() {
    createNewsUseCaseService = new CreateNewsUseCaseService(newsRepository, categoryRepository);
  }

  @Test
  void shouldAddNews() {
    news = NewsBuilder.random();

    createNewsUseCaseService.add(news);

    verify(newsRepository).add(news);
  }

}
