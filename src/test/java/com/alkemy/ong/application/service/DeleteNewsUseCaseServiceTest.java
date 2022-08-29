package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteNewsUseCaseServiceTest {

  private DeleteNewsUseCaseService newsService;

  @Mock
  private INewsRepository newsRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    newsService = new DeleteNewsUseCaseService(newsRepository);
  }

  @Test
  void shouldThrowAnExceptionWhenNewsDoesNotExist() {
    given(newsRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFound.class, () -> newsService.delete(identifiable));
  }

  @Test
  void shouldDeleteNewsWhenNewsExist() {
    given(newsRepository.exists(identifiable)).willReturn(true);

    newsService.delete(identifiable);

    verify(newsRepository).exists(identifiable);
    verify(newsRepository).delete(identifiable);
  }
}