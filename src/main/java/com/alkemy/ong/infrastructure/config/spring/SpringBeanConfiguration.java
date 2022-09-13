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
import com.alkemy.ong.application.service.activity.CreateActivityUseCaseService;
import com.alkemy.ong.application.service.activity.UpdateActivityUseCaseService;
import com.alkemy.ong.application.service.activity.usecase.ICreateActivityUseCase;
import com.alkemy.ong.application.service.activity.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.application.service.category.CreateCategoryUseCaseService;
import com.alkemy.ong.application.service.category.DeleteCategoryUseCaseService;
import com.alkemy.ong.application.service.category.GetCategoryUseCase;
import com.alkemy.ong.application.service.category.ListCategoryUseCaseService;
import com.alkemy.ong.application.service.category.UpdateCategoryUserCaseService;
import com.alkemy.ong.application.service.category.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IListCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.application.service.comment.CreateCommentUseCaseService;
import com.alkemy.ong.application.service.comment.DeleteCommentUseCaseService;
import com.alkemy.ong.application.service.comment.ListCommentUseCaseService;
import com.alkemy.ong.application.service.comment.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.comment.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.comment.usecase.IListCommentUseCase;
import com.alkemy.ong.application.service.contact.CreateContactUseCaseService;
import com.alkemy.ong.application.service.contact.ListContactUseCaseService;
import com.alkemy.ong.application.service.contact.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.contact.usecase.IListContactUseCase;
import com.alkemy.ong.application.service.delegate.IAuthenticationManager;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.application.service.member.DeleteMemberUseCaseService;
import com.alkemy.ong.application.service.member.ListMemberUseCaseService;
import com.alkemy.ong.application.service.member.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.member.usecase.IListMemberUseCase;
import com.alkemy.ong.application.service.news.CreateNewsUseCaseService;
import com.alkemy.ong.application.service.news.DeleteNewsUseCaseService;
import com.alkemy.ong.application.service.news.GetNewsUseCaseService;
import com.alkemy.ong.application.service.news.GetNewsWithCommentsUseCaseService;
import com.alkemy.ong.application.service.news.UpdateNewsUseCaseService;
import com.alkemy.ong.application.service.news.usecase.ICreateNewsUseCase;
import com.alkemy.ong.application.service.news.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.news.usecase.IGetNewsUseCase;
import com.alkemy.ong.application.service.news.usecase.IGetNewsWithCommentsUseCase;
import com.alkemy.ong.application.service.news.usecase.IUpdateNewsUseCase;
import com.alkemy.ong.application.service.organization.GetOrganizationUseCaseService;
import com.alkemy.ong.application.service.organization.UpdateOrganizationUseCaseService;
import com.alkemy.ong.application.service.organization.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.organization.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.application.service.slide.CreateSlideUseCaseService;
import com.alkemy.ong.application.service.slide.DeleteSlideUseCaseService;
import com.alkemy.ong.application.service.slide.GetSlideUseCaseService;
import com.alkemy.ong.application.service.slide.ListSlideUseCaseService;
import com.alkemy.ong.application.service.slide.UpdateSlideUseCaseService;
import com.alkemy.ong.application.service.slide.usecase.ICreateSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IListSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IUpdateSlideUseCase;
import com.alkemy.ong.application.service.testimonial.CreateTestimonialUseCaseService;
import com.alkemy.ong.application.service.testimonial.DeleteTestimonialUseCaseService;
import com.alkemy.ong.application.service.testimonial.usecase.ICreateTestimonialUseCase;
import com.alkemy.ong.application.service.testimonial.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.user.CreateUserUseCaseService;
import com.alkemy.ong.application.service.user.DeleteUserUseCaseService;
import com.alkemy.ong.application.service.user.GetUserUseCaseService;
import com.alkemy.ong.application.service.user.ListUserUseCaseService;
import com.alkemy.ong.application.service.user.LoginUserUseCaseService;
import com.alkemy.ong.application.service.user.UpdateUserUseCaseService;
import com.alkemy.ong.application.service.user.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.user.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.user.usecase.IGetUserUseCase;
import com.alkemy.ong.application.service.user.usecase.IListUserUseCase;
import com.alkemy.ong.application.service.user.usecase.ILoginUserUseCase;
import com.alkemy.ong.application.service.user.usecase.IUpdateUserUseCase;
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
  public ICreateContactUseCase createContactUseCase(IContactRepository contactRepository,
      IOrganizationRepository organizationRepository, IMailSender mailSender) {
    return new CreateContactUseCaseService(contactRepository, organizationRepository, mailSender);
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
  public IUpdateNewsUseCase updateNewsUseCase(INewsRepository newsRepository) {
    return new UpdateNewsUseCaseService(newsRepository);
  }

  @Bean
  public ICreateTestimonialUseCase createTestimonialUseCase(
      ITestimonialRepository testimonialRepository) {
    return new CreateTestimonialUseCaseService(testimonialRepository);
  }

  @Bean
  public IGetUserUseCase getUserUseCase(IUserRepository userRepository) {
    return new GetUserUseCaseService((userRepository));
  }

}