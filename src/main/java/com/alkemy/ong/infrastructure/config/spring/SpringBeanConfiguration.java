package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.CreateUserUseCaseService;
import com.alkemy.ong.application.service.DeleteCategoryUseCaseService;
import com.alkemy.ong.application.service.DeleteCommentUseCaseService;
import com.alkemy.ong.application.service.DeleteMemberUseCaseService;
import com.alkemy.ong.application.service.DeleteNewsUseCaseService;
import com.alkemy.ong.application.service.DeleteSlideUseCaseService;
import com.alkemy.ong.application.service.DeleteTestimonialUseCaseService;
import com.alkemy.ong.application.service.DeleteUserUseCaseService;
import com.alkemy.ong.application.service.GetOrganizationUseCaseService;
import com.alkemy.ong.application.service.ListSlideUseCaseService;
import com.alkemy.ong.application.service.UpdateCategoryUserCaseService;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.application.service.usecase.IOperationAllowed;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.infrastructure.database.repository.CategoryRepository;
import com.alkemy.ong.infrastructure.database.repository.SlideRepository;
import com.alkemy.ong.infrastructure.database.repository.UserRepository;
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

  @Bean
  public IGetOrganizationUseCase getOrganizationUseCase(
      IOrganizationRepository organizationRepository) {
    return new GetOrganizationUseCaseService(organizationRepository);
  }

  @Bean
  public IDeleteNewsUseCase deleteNewsUseCase(INewsRepository newsRepository) {
    return new DeleteNewsUseCaseService(newsRepository);
  }

  @Bean
  public IDeleteCategoryUseCase deleteCategoryUseCase(ICategoryRepository categoryRepository) {
    return new DeleteCategoryUseCaseService(categoryRepository);
  }

  @Bean
  public IDeleteCommentUseCase deleteCommentUseCase(ICommentRepository commentRepository,
      IOperationAllowed authorization) {
    return new DeleteCommentUseCaseService(commentRepository, authorization);
  }

  @Bean
  public ICreateUserUseCase createUserService(UserRepository userRepository,
      IRoleRepository roleRepository) {
    return new CreateUserUseCaseService(userRepository, roleRepository);
  }

  @Bean
  public IListSlideUseCase listSlideUseCaseService(SlideRepository slideRepository) {
    return new ListSlideUseCaseService(slideRepository);
  }

  @Bean
  public IUpdateCategoryUseCase updateCategoryUseCase(CategoryRepository categoryRepository) {
    return new UpdateCategoryUserCaseService(categoryRepository);
  }
}