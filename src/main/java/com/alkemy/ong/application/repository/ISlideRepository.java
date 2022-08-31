package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Slide;
import java.util.List;

public interface ISlideRepository {

  boolean exists(Identifiable<Long> identifiable);

  void delete(Identifiable<Long> identifiable);

  List<Slide> findAllByOrderByOrder();
}
