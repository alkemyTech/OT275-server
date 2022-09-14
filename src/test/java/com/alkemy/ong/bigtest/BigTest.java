package com.alkemy.ong.bigtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.alkemy.ong.OngApplication;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IActivitySpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ICategorySpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ICommentSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.INewsSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IOrganizationSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IRoleSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import com.alkemy.ong.infrastructure.rest.request.user.AuthenticationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.main.allow-bean-definition-overriding=true",
    classes = OngApplication.class)
@AutoConfigureMockMvc
public abstract class BigTest {

  protected static final String ADMIN_EMAIL = "jason@voorhees.com";
  protected static final String USER_EMAIL = "freedy@krueger.com";
  protected static final String INVALID_TOKEN = "invalid-token";
  protected static final String ACCESS_DENIED_MESSAGE = "Access denied.";
  protected static final String ACCESS_DENIED_MORE_INFO = "Access Denied. Contact your administrator.";
  protected static final String OBJECT_NOT_FOUND_MESSAGE = "Object not found in database.";
  protected static final String INVALID_INPUT_DATA_MESSAGE = "Invalid input data.";
  protected static final String LINK_HEADER = "Link";

  private static final String PASSWORD_ENCODED = "$2a$10$6KLdPa9azXYgkMOo1zw16.JSngJvSGRvPqokwzi9vzO4OJLKS2bX2";
  private static final String PASSWORD = "abcd1234";
  private static final String BEARER_PART = "Bearer ";
  private static final String NONE_IMAGE_URL = "";

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  protected IUserSpringRepository userRepository;

  @Autowired
  protected IRoleSpringRepository roleRepository;

  @Autowired
  protected IOrganizationSpringRepository organizationRepository;

  @Autowired
  protected ICategorySpringRepository categoryRepository;

  @Autowired
  protected INewsSpringRepository newsRepository;

  @Autowired
  protected ICommentSpringRepository commentRepository;

  @Autowired
  protected IActivitySpringRepository activityRepository;

  @Before
  public void setup() {
    createRoles();
    createUserData();
    createOrganization();
    createNewsCategory();
  }

  @After
  public void tearDown() {
    deleteAllEntities();
  }

  private void deleteAllEntities() {
    organizationRepository.deleteAll();
    commentRepository.deleteAll();
    newsRepository.deleteAll();
    categoryRepository.deleteAll();
    activityRepository.deleteAll();
  }

  protected void cleanUsersData(UserEntity... users) {
    userRepository.deleteAllInBatch(Arrays.asList(users));
  }

  private void createNewsCategory() {
    if (categoryRepository.findByNameIgnoreCase("news") == null) {
      categoryRepository.save(buildCategory("News", "News description", NONE_IMAGE_URL));
    }
  }

  private void createUserData() {
    if (userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
      saveAdminUser();
    }
    if (userRepository.findByEmail(USER_EMAIL).isEmpty()) {
      saveStandardUser();
    }
  }

  private void createRoles() {
    if (roleRepository.count() == 0) {
      roleRepository.saveAll(List.of(
          buildRole(Role.USER),
          buildRole(Role.ADMIN)));
    }
  }

  private void saveStandardUser() {
    userRepository.save(buildUser(
        "Freddy",
        "Krueger",
        USER_EMAIL,
        Role.USER));
  }

  private void saveAdminUser() {
    userRepository.save(buildUser(
        "Jason",
        "Voorhees",
        ADMIN_EMAIL,
        Role.ADMIN));
  }

  private UserEntity buildUser(String firstName, String lastName, String email, Role role) {
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(firstName);
    userEntity.setLastName(lastName);
    userEntity.setEmail(email);
    userEntity.setPassword(PASSWORD_ENCODED);
    userEntity.setRole(roleRepository.findByName(role.getFullRoleName()));
    userEntity.setSoftDeleted(false);
    return userEntity;
  }

  private RoleEntity buildRole(Role role) {
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setDescription(role.name());
    roleEntity.setName(role.getFullRoleName());
    return roleEntity;
  }

  private NewsEntity buildNews(String name) {
    NewsEntity newsEntity = new NewsEntity();
    newsEntity.setImageUrl(NONE_IMAGE_URL);
    newsEntity.setContent("Content for " + name);
    newsEntity.setName(name);
    newsEntity.setCategory(categoryRepository.findByNameIgnoreCase("news"));
    newsEntity.setSoftDeleted(false);
    return newsEntity;
  }

  private CommentEntity buildComment(Long newsId) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setBody("Awesome post!");
    commentEntity.setUser(userRepository.findByEmail(USER_EMAIL).get());
    commentEntity.setNews(newsRepository.findById(newsId).get());
    return commentEntity;
  }

  private void createOrganization() {
    organizationRepository.save(buildOrganization());
  }

  private OrganizationEntity buildOrganization() {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    organizationEntity.setName("Somos Mas");
    organizationEntity.setImageUrl("https://s3.com/logo.jpg/");
    organizationEntity.setWelcomeText("Welcome to Somos Mas");
    organizationEntity.setEmail("somos.mas@ong.com");
    organizationEntity.setPhone("+540303456");
    organizationEntity.setAddress("Elm Street 3");
    organizationEntity.setFacebookUrl("https://www.facebook.com/Somos_Mas/");
    organizationEntity.setLinkedInUrl("https://www.linkedin.com/in/Somos-Mas/");
    organizationEntity.setInstagramUrl("https://www.instagram.com/SomosMas/");
    organizationEntity.setSoftDeleted(false);
    return organizationEntity;
  }

  protected String getAuthorizationTokenForAdminUser() throws Exception {
    return getAuthorizationTokenForUser(ADMIN_EMAIL);
  }

  protected String getAuthorizationTokenForStandardUser() throws Exception {
    return getAuthorizationTokenForUser(USER_EMAIL);
  }

  private String getAuthorizationTokenForUser(String email) throws Exception {
    String content = mockMvc.perform(post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new AuthenticationRequest(email, PASSWORD))))
        .andReturn()
        .getResponse().getContentAsString(StandardCharsets.UTF_8);

    return BEARER_PART + JsonPath.read(content, "$.token");
  }

  protected UserEntity getRandomUser() {
    return userRepository.save(buildUser("Michael", "Myers", "michael@myers.com", Role.USER));
  }

  protected NewsEntity createNewsWithRandomComment(String name) {
    NewsEntity newsEntity = createNews(name);
    saveCommentFor(newsEntity.getNewsId());
    return newsEntity;
  }

  protected NewsEntity createNews(String name) {
    return newsRepository.save(buildNews(name));
  }

  private CommentEntity saveCommentFor(Long newsId) {
    return commentRepository.save(buildComment(newsId));
  }

  protected Long getRandomCommentId() {
    NewsEntity newsEntity = createNews("News for comment");
    return saveCommentFor(newsEntity.getNewsId()).getCommentId();
  }

  protected Long getRandomCategoryId() {
    CategoryEntity randomCategory = getRandomCategory();
    return randomCategory.getCategoryId();
  }

  protected CategoryEntity getRandomCategory() {
    return categoryRepository.save(buildCategory(
        "Sports",
        "Sports description",
        "https://s3.com/sports-category.jpg"));
  }

  protected CategoryEntity buildCategory(String name, String description, String imageUrl) {
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setName(name);
    categoryEntity.setDescription(description);
    categoryEntity.setImageUrl(imageUrl);
    categoryEntity.setSoftDeleted(false);
    return categoryEntity;
  }

  protected void deleteCategory(Long categoryId) {
    categoryRepository.deleteById(categoryId);
  }

  protected Long getRandomActivityId() {
    ActivityEntity randomActivity = getRandomActivity();
    return randomActivity.getActivityId();
  }

  protected ActivityEntity getRandomActivity() {
    return activityRepository.save(buildActivity(
        "My Activity",
        "Activity content",
        "https://s3.com/my-activity.jpg"));
  }

  protected ActivityEntity buildActivity(String name, String content, String image) {
    ActivityEntity activityEntity = new ActivityEntity();
    activityEntity.setName(name);
    activityEntity.setContent(content);
    activityEntity.setImageUrl(image);
    activityEntity.setSoftDeleted(false);
    return activityEntity;
  }

  protected String convert(Object requestObject) throws JsonProcessingException {
    return objectMapper.writeValueAsString(requestObject);
  }

}
