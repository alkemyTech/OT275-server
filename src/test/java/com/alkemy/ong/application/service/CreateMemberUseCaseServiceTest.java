package com.alkemy.ong.application.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.member.CreateMemberUseCaseService;
import com.alkemy.ong.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateMemberUseCaseServiceTest {

  private CreateMemberUseCaseService createMemberUseCaseService;

  @Mock
  private IMemberRepository memberRepository;

  @Mock
  private Member member;

  @BeforeEach
  void setUp() {
    createMemberUseCaseService = new CreateMemberUseCaseService(memberRepository);
  }

  @Test
  void shouldCreateMember() {
    given(memberRepository.add(member)).willReturn(member);

    createMemberUseCaseService.add(member);

    verify(memberRepository, times(1)).add(member);
  }

}
