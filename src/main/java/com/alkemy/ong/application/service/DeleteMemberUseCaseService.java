package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.domain.Identifiable;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DeleteMemberUseCaseService implements IDeleteMemberUseCase {

  private final IMemberRepository memberRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!memberRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Member"));
    }
    memberRepository.delete(identifiable);
  }
}
