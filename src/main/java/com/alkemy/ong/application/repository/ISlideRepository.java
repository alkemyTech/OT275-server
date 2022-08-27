package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;

public interface ISlideRepository {

  boolean exists(Identifiable<Long> identifiable);

  void delete(Identifiable<Long> identifiable);
}
