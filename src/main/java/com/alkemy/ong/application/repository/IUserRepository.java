package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.User;
import java.util.Optional;

public interface IUserRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  Optional<User> findBy(String email);

  User add(User user);

  Optional<User> findById(Identifiable<Long> identifiable);

  User update(User user);

}