package com.alkemy.ong.application.service.member;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.member.usecase.ICreateMemberUseCase;
import com.alkemy.ong.domain.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateMemberUseCaseService implements ICreateMemberUseCase {

  private final IMemberRepository memberRepository;

  @Override
  public Member add(Member member) {
    return memberRepository.add(member);
  }

}
