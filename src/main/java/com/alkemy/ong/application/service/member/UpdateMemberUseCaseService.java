package com.alkemy.ong.application.service.member;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.member.usecase.IUpdateMemberUseCase;
import com.alkemy.ong.domain.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateMemberUseCaseService implements IUpdateMemberUseCase {

  private final IMemberRepository memberRepository;

  @Override
  public Member update(Member member) {
    if (!memberRepository.exists(member::getMemberId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Member"));
    }
    return memberRepository.update(member);
  }
}
