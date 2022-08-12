package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IFooRepository;
import com.alkemy.ong.domain.Foo;
import com.alkemy.ong.infrastructure.database.mapper.FooEntityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FooRepository implements IFooRepository {

  private final IFooSpringRepository fooSpringRepository;
  private final FooEntityMapper fooEntityMapper;

  @Override
  public List<Foo> findAll() {
    return fooEntityMapper.toDomain(fooSpringRepository.findAll());
  }

}
