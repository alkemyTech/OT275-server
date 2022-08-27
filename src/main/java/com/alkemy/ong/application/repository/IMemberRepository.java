package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;

public interface IMemberRepository {

  void delete(Identifiable<Long> identificable);

  boolean exists(Identifiable<Long> identificable);

}
