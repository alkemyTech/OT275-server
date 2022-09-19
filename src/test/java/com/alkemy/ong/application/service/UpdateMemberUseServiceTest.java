package com.alkemy.ong.application.service;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.member.UpdateMemberUseCaseService;
import com.alkemy.ong.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateMemberUseCaseServiceTest {

  private UpdateMemberUseCaseService updateMemberUseCaseService;

  @Mock
  private IMemberRepository memberRepository;

  @Mock
  private Member member;

  @BeforeEach
  void setUp() {
    updateMemberUseCaseService = new UpdateMemberUseCaseService(memberRepository);
  }

  @Test
  void shouldThrowExceptionWhenTestimonialDoesNotExist() {
    given(memberRepository.exists(any())).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> updateMemberUseCaseService.update(member));
  }

  @Test
  void shouldUpdateTestimonial() {
    given(memberRepository.exists(any())).willReturn(true);
    given(memberRepository.update(member)).willReturn(member);

    updateMemberUseCaseService.update(member);

    verify(memberRepository).update(member);
  }

}
