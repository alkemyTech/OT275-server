package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.usecase.IListMemberUseCase;
import com.alkemy.ong.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ListMemberUseCaseService implements IListMemberUseCase {

  private final IMemberRepository memberRepository;

  @Override
  public Page<Member> findAll(Pageable pageable) {
    return memberRepository.findAll(pageable);
  }
}
