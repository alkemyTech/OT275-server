package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.service.FooService;
import com.alkemy.ong.application.service.usecase.IListFooUseCase;
import com.alkemy.ong.infrastructure.database.repository.FooRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfig {

  @Bean
  public IListFooUseCase listFooService(FooRepository fooRepository) {
    return new FooService(fooRepository);
  }

}
