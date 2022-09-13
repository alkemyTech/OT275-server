package com.alkemy.ong.application.service.user.usecase;

import com.alkemy.ong.domain.User;
import java.util.List;

public interface IListUserUseCase {

  List<User> findAll();

}
