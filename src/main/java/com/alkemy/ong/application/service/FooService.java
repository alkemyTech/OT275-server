package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IFooRepository;
import com.alkemy.ong.application.service.usecase.IListFooUseCase;
import com.alkemy.ong.domain.Foo;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FooService implements IListFooUseCase {

  private final IFooRepository fooRepository;

  @Override
  public List<Foo> findAll() {
    return fooRepository.findAll();
  }

}
