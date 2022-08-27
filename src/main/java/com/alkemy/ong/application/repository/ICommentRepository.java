package com.alkemy.ong.application.repository;


import com.alkemy.ong.domain.Identifiable;

public interface ICommentRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

}