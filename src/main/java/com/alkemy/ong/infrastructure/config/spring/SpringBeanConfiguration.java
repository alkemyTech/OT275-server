package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.CreateSlideUseCaseService;
import com.alkemy.ong.application.service.CreateUserUseCaseService;
import com.alkemy.ong.application.service.DeleteCategoryUseCaseService;
import com.alkemy.ong.application.service.DeleteCommentUseCaseService;
import com.alkemy.ong.application.service.DeleteMemberUseCaseService;
import com.alkemy.ong.application.service.DeleteNewsUseCaseService;
import com.alkemy.ong.application.service.DeleteSlideUseCaseService;
import com.alkemy.ong.application.service.DeleteTestimonialUseCaseService;
import com.alkemy.ong.application.service.DeleteUserUseCaseService;
import com.alkemy.ong.application.service.GetCategoryUseCase;
import com.alkemy.ong.application.service.GetOrganizationUseCaseService;
import com.alkemy.ong.application.service.GetSlideUseCaseService;
import com.alkemy.ong.application.service.ListSlideUseCaseService;
import com.alkemy.ong.application.service.LoginUserUseCaseService;
import com.alkemy.ong.application.service.UpdateActivityUseCaseService;
import com.alkemy.ong.application.service.UpdateCategoryUserCaseService;
import com.alkemy.ong.application.service.UpdateOrganizationUseCaseService;
import com.alkemy.ong.application.service.delegate.IAuthenticationManager;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.application.service.usecase.ICreateSlideUseCase;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.application.service.usecase.ILoginUserUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.application.util.IImageUploader;
import com.alkemy.ong.infrastructure.database.repository.CategoryRepository;
import com.alkemy.ong.infrastructure.database.repository.SlideRepository;
import com.alkemy.ong.infrastructure.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
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
      IOrganizationRepository organizationRepository, ISlideRepository slideRepository) {
    return new GetOrganizationUseCaseService(organizationRepository, slideRepository);
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
  public IGetCategoryUseCase getCategoryUseCase(ICategoryRepository categoryRepository) {
    return new GetCategoryUseCase(categoryRepository);
  }

  @Bean
  public IListSlideUseCase listSlideUseCaseService(SlideRepository slideRepository) {
    return new ListSlideUseCaseService(slideRepository);
  }

  @Bean
  public IUpdateCategoryUseCase updateCategoryUseCase(CategoryRepository categoryRepository) {
    return new UpdateCategoryUserCaseService(categoryRepository);
  }

  @Bean
  public ILoginUserUseCase loginUserCase(IUserRepository userRepository,
      IAuthenticationManager authenticationManager) {
    return new LoginUserUseCaseService(userRepository, authenticationManager);
  }

  @Bean
  public IGetSlideUseCase getSlideUseCase(ISlideRepository slideRepository) {
    return new GetSlideUseCaseService(slideRepository);
  }

  @Bean
  public IUpdateOrganizationUseCase updateOrganizationUseCase(
      IOrganizationRepository organizationRepository) {
    return new UpdateOrganizationUseCaseService(organizationRepository);
  }

  @Bean
  public IUpdateActivityUseCase updateActivityUseCase(IActivityRepository activityRepository) {
    return new UpdateActivityUseCaseService(activityRepository);
  }

  @Bean
  public ICreateSlideUseCase createSlideUseCase(ISlideRepository slideRepository,
      @Qualifier("s3Utils") IImageUploader imageUploader) {
    return new CreateSlideUseCaseService(slideRepository, imageUploader);
  }

}