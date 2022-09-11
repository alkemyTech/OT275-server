package com.alkemy.ong.application.service;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListMemberUseCaseServiceTest {

  private ListMemberUseCaseService listMemberUseCaseService;
  @Mock
  private IMemberRepository memberRepository;

  @BeforeEach
  void setUp() {
    listMemberUseCaseService = new ListMemberUseCaseService(memberRepository);
  }

  @Test
  void shouldReturnPageable() {
    Pageable pageable = Pageable.unpaged();
    Page<Member> page = Page.empty();

    given(memberRepository.findAll(pageable)).willReturn(page);
    listMemberUseCaseService.findAll(pageable);

    verify(memberRepository, times(1)).findAll(pageable);
  }
}