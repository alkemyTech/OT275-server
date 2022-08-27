package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.DeleteCommentUseCaseService;
import com.alkemy.ong.application.service.DeleteSlideUseCaseService;
import com.alkemy.ong.application.service.DeleteUserUseCaseService;
import com.alkemy.ong.application.service.usecase.IAuthorization;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

  @Bean
  public IDeleteUserUseCase deleteUserUseCase(IUserRepository userRepository) {
    return new DeleteUserUseCaseService(userRepository);
  }

  @Bean
  public IDeleteSlideUseCase deleteSlideUseCase(ISlideRepository slideRepository) {
    return new DeleteSlideUseCaseService(slideRepository);
  }

  @Bean
  public IDeleteCommentUseCase deleteCommentUseCase(ICommentRepository commentRepository,
      IAuthorization authorization) {
    return new DeleteCommentUseCaseService(commentRepository, authorization);
  }

}