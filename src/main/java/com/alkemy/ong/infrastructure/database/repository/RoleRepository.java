package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IRoleSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleRepository implements IRoleRepository {

  private final IRoleSpringRepository roleSpringRepository;

}
