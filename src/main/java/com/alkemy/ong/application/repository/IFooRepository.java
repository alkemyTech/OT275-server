package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Foo;
import java.util.List;

public interface IFooRepository {

  List<Foo> findAll();

}
