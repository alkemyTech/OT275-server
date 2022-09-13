package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.news.DeleteNewsUseCaseService;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteNewsUseCaseServiceTest {

  private DeleteNewsUseCaseService deleteNewsUseCaseService;

  @Mock
  private INewsRepository newsRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    deleteNewsUseCaseService = new DeleteNewsUseCaseService(newsRepository);
  }

  @Test
  void shouldThrowAnExceptionWhenNewsDoesNotExist() {
    given(newsRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> deleteNewsUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteNewsWhenNewsExist() {
    given(newsRepository.exists(identifiable)).willReturn(true);

    deleteNewsUseCaseService.delete(identifiable);

    verify(newsRepository).exists(identifiable);
    verify(newsRepository).delete(identifiable);
  }
}