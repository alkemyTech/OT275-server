package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  News get(Identifiable<Long> identifiable);

  News getWithComments(Identifiable<Long> identifiable);

  News add(News news);

  News update(News news);

  Page<News> findAll(Pageable pageable);

}
