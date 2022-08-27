package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.DeleteMemberUseCaseService;
import com.alkemy.ong.application.service.DeleteSlideUseCaseService;
import com.alkemy.ong.application.service.DeleteTestimonialUseCaseService;
import com.alkemy.ong.application.service.DeleteUserUseCaseService;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

  @Bean
  public IDeleteMemberUseCase deleteMemberUseCase(IMemberRepository memberRepository) {
    return new DeleteMemberUseCaseService(memberRepository);
  }

  @Bean
  public IDeleteUserUseCase deleteUserUseCase(IUserRepository userRepository) {
    return new DeleteUserUseCaseService(userRepository);
  }

  @Bean
  public IDeleteSlideUseCase deleteSlideUseCase(ISlideRepository slideRepository) {
    return new DeleteSlideUseCaseService(slideRepository);
  }

  @Bean
  public IDeleteTestimonialUseCase deleteTestimonialUseCase(
      ITestimonialRepository testimonialRepository) {
    return new DeleteTestimonialUseCaseService(testimonialRepository);
  }

}