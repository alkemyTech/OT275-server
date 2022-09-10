package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.CreateActivityUseCaseService;
import com.alkemy.ong.application.service.CreateCategoryUseCaseService;
import com.alkemy.ong.application.service.CreateCommentUseCaseService;
import com.alkemy.ong.application.service.CreateContactUseCaseService;
import com.alkemy.ong.application.service.CreateNewsUseCaseService;
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
import com.alkemy.ong.application.service.GetNewsUseCaseService;
import com.alkemy.ong.application.service.GetNewsWithCommentsUseCaseService;
import com.alkemy.ong.application.service.GetOrganizationUseCaseService;
import com.alkemy.ong.application.service.GetSlideUseCaseService;
import com.alkemy.ong.application.service.GetUserUseCaseService;
import com.alkemy.ong.application.service.ListCategoryUseCaseService;
import com.alkemy.ong.application.service.ListCommentUseCaseService;
import com.alkemy.ong.application.service.ListContactUseCaseService;
import com.alkemy.ong.application.service.ListMemberUseCaseService;
import com.alkemy.ong.application.service.ListSlideUseCaseService;
import com.alkemy.ong.application.service.ListUserUseCaseService;
import com.alkemy.ong.application.service.LoginUserUseCaseService;
import com.alkemy.ong.application.service.UpdateActivityUseCaseService;
import com.alkemy.ong.application.service.UpdateCategoryUserCaseService;
import com.alkemy.ong.application.service.UpdateOrganizationUseCaseService;
import com.alkemy.ong.application.service.UpdateSlideUseCaseService;
import com.alkemy.ong.application.service.UpdateUserUseCaseService;
import com.alkemy.ong.application.service.delegate.IAuthenticationManager;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.application.service.usecase.ICreateActivityUseCase;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.usecase.ICreateNewsUseCase;
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
import com.alkemy.ong.application.service.usecase.IGetNewsUseCase;
import com.alkemy.ong.application.service.usecase.IGetNewsWithCommentsUseCase;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.usecase.IGetUserUseCase;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IListCommentUseCase;
import com.alkemy.ong.application.service.usecase.IListContactUseCase;
import com.alkemy.ong.application.service.usecase.IListMemberUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.application.service.usecase.ILoginUserUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateSlideUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateUserUseCase;
import com.alkemy.ong.application.util.image.IImageUploader;
import com.alkemy.ong.application.util.mail.IMailSender;
import com.alkemy.ong.infrastructure.database.repository.CategoryRepository;
import com.alkemy.ong.infrastructure.database.repository.SlideRepository;
import com.alkemy.ong.infrastructure.database.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

  @Bean
  public IListMemberUseCase listMemberUseCase(IMemberRepository memberRepository) {
    return new ListMemberUseCaseService(memberRepository);
  }

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
  public IListCommentUseCase listCommentUseCase(ICommentRepository commentRepository) {
    return new ListCommentUseCaseService(commentRepository);
  }

  @Bean
  public ICreateCommentUseCase createCommentUseCase(ICommentRepository commentRepository,
      IUserRepository userRepository,
      INewsRepository newsRepository) {
    return new CreateCommentUseCaseService(commentRepository, userRepository, newsRepository);
  }

  @Bean
  public IDeleteCommentUseCase deleteCommentUseCase(ICommentRepository commentRepository,
      IOperationAllowed authorization) {
    return new DeleteCommentUseCaseService(commentRepository, authorization);
  }

  @Bean
  public ICreateUserUseCase createUserService(UserRepository userRepository,
      IRoleRepository roleRepository, IOrganizationRepository organizationRepository,
      IMailSender mailSender) {
    return new CreateUserUseCaseService(userRepository, roleRepository, organizationRepository,
        mailSender);
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
  public ICreateCategoryUseCase createCategoryUseCase(CategoryRepository categoryRepository) {
    return new CreateCategoryUseCaseService(categoryRepository);
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
  public IListCategoryUseCase listCategoryUseCaseService(ICategoryRepository categoryRepository) {
    return new ListCategoryUseCaseService(categoryRepository);
  }

  @Bean
  public IUpdateActivityUseCase updateActivityUseCase(IActivityRepository activityRepository) {
    return new UpdateActivityUseCaseService(activityRepository);
  }

  @Bean
  public ICreateSlideUseCase createSlideUseCase(ISlideRepository slideRepository,
      IImageUploader imageUploader) {
    return new CreateSlideUseCaseService(slideRepository, imageUploader);
  }

  @Bean
  public IListUserUseCase listUserUseCase(IUserRepository userRepository) {
    return new ListUserUseCaseService(userRepository);
  }

  @Bean
  public IUpdateUserUseCase updateUserUseCase(IUserRepository userRepository) {
    return new UpdateUserUseCaseService(userRepository);

  }

  @Bean
  public ICreateActivityUseCase createActivityUseCase(IActivityRepository activityRepository) {
    return new CreateActivityUseCaseService(activityRepository);
  }

  @Bean
  public IGetNewsUseCase getNewsUseCase(INewsRepository newsRepository) {
    return new GetNewsUseCaseService(newsRepository);
  }

  @Bean
  public IGetNewsWithCommentsUseCase getNewsWithCommentsUseCase(
      INewsRepository newsRepository) {
    return new GetNewsWithCommentsUseCaseService(newsRepository);
  }

  @Bean
  public ICreateContactUseCase createContactUseCase(IContactRepository contactRepository) {
    return new CreateContactUseCaseService(contactRepository);
  }

  @Bean
  public IListContactUseCase listContactUseCase(IContactRepository contactRepository) {
    return new ListContactUseCaseService(contactRepository);
  }

  @Bean
  public ICreateNewsUseCase createNewsUseCase(INewsRepository newsRepository,
      ICategoryRepository categoryRepository) {
    return new CreateNewsUseCaseService(newsRepository, categoryRepository);
  }

  @Bean
  public IUpdateSlideUseCase updateSlideUseCase(ISlideRepository slideRepository) {
    return new UpdateSlideUseCaseService(slideRepository);
  }

  @Bean
  public IGetUserUseCase getUserUseCase(IUserRepository userRepository) {
    return new GetUserUseCaseService((userRepository));
  }

}