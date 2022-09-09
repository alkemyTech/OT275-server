package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;

public interface INewsRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  News get(Identifiable<Long> identifiable);

  News getWithComments(Identifiable<Long> identifiable);
}
