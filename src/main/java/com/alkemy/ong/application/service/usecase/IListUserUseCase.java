package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.User;
import java.util.List;

public interface IListUserUseCase {

  List<User> findAll();

}
