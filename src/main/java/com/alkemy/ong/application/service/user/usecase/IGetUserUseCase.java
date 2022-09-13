package com.alkemy.ong.application.service.user.usecase;

import com.alkemy.ong.domain.User;

public interface IGetUserUseCase {

  User get(String name);

}
