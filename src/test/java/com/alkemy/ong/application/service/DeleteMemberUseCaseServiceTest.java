package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteMemberUseCaseServiceTest {

  private DeleteMemberUseCaseService deleteMemberUseCaseService;

  @Mock
  private IMemberRepository memberRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    deleteMemberUseCaseService = new DeleteMemberUseCaseService(memberRepository);
  }

  @Test
  void shouldThrowExceptionWhenMemberDoesNotExist() {
    given(memberRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFound.class, () -> deleteMemberUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteMemberWhenMemberExist() {
    given(memberRepository.exists(identifiable)).willReturn(true);

    deleteMemberUseCaseService.delete(identifiable);

    verify(memberRepository).exists(identifiable);
    verify(memberRepository).delete(identifiable);
  }

}