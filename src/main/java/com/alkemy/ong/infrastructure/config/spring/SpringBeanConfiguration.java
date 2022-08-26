package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.DeleteUserUseCaseService;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

  @Bean
  public IDeleteUserUseCase deleteUserUseCase(IUserRepository userRepository) {
    return new DeleteUserUseCaseService(userRepository);
  }
}