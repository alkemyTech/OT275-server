package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.repository.MemberRepository;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DeleteMemberUseCaseService implements IDeleteMemberUseCase {

  private final IMemberRepository memberRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!MemberRepository.exists(identifiable)) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Member"));
    }
    memberRepository.delete(identifiable);
  }
}


}
