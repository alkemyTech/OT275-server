package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.news.ListNewsUseCaseService;
import com.alkemy.ong.domain.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ListNewsUseCaseServiceTest {

  private ListNewsUseCaseService listNewsUseCaseService;

  @Mock
  private INewsRepository newsRepository;

  @BeforeEach
  void setUp() {
    listNewsUseCaseService = new ListNewsUseCaseService(newsRepository);
  }

  @Test
  void shouldReturnPage() {
    Pageable pageable = Pageable.unpaged();
    Page<News> page = Page.empty();

    given(newsRepository.findAll(pageable)).willReturn(page);

    Page<News> actualPage = listNewsUseCaseService.findAll(pageable);

    assertNotNull(actualPage);
    verify(newsRepository, times(1)).findAll(pageable);
  }
}
