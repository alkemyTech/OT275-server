package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IMemberSpringRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MemberRepository implements IMemberRepository {

  private final IMemberSpringRepository memberSpringRepository;

}
