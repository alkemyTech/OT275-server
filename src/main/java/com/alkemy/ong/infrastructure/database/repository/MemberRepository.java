package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IMemberSpringRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MemberRepository implements IMemberRepository {

  private final IMemberSpringRepository memberSpringRepository;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    memberSpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return memberSpringRepository.exists(identifiable.getId()).isPresent();
  }
}