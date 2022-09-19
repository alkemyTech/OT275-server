package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import com.alkemy.ong.infrastructure.database.mapper.MemberEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IMemberSpringRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MemberRepository implements IMemberRepository {

  private final IMemberSpringRepository memberSpringRepository;
  private final MemberEntityMapper memberEntityMapper;

  @Override
  public Page<Member> findAll(Pageable pageable) {
    Page<MemberEntity> members = memberSpringRepository.findAllBySoftDeletedFalse(pageable);
    return memberEntityMapper.toPageDomain(
        members.getContent(),
        members.getNumber(),
        members.getSize(),
        members.getTotalElements()
    );
  }

  @Override
  public Member add(Member member) {
    MemberEntity memberEntity = memberEntityMapper.toEntity(member);
    memberEntity.setSoftDeleted(false);
    return memberEntityMapper.toDomain(memberSpringRepository.save(memberEntity));
  }

  @Override
  public void delete(Identifiable<Long> identifiable) {
    memberSpringRepository.softDelete(identifiable.getId());

  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return memberSpringRepository.exists(identifiable.getId()).isPresent();

  }

  @Override
  public Member update(Member member) {
    MemberEntity memberEntity = memberEntityMapper.toEntity(member);
    return memberEntityMapper.toDomain(memberSpringRepository.save(memberEntity));
  }

}