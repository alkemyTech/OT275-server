package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMemberRepository {

  void delete(Identifiable<Long> identificable);

  boolean exists(Identifiable<Long> identificable);

  Page<Member> findAll(Pageable pageable);

  Member add(Member member);

  Member update(Member member);
}
