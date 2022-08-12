package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Foo;
import java.util.List;

public interface IListFooUseCase {

  List<Foo> findAll();

}
