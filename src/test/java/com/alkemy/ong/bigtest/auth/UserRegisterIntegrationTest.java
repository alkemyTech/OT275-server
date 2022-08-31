package com.alkemy.ong.bigtest.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.MediaType;

public class UserRegisterIntegrationTest extends BigTest {

  private static final String URL = "/auth/register";
  private static final String FIRST_NAME = "Charles Lee";
  private static final String LAST_NAME = "Ray";
  private static final String PASSWORD = "abcd1234";
  private static final String EMAIL = "charles.lee@ray.com";

  @Test
  public void shouldCreateUserWhenRequestIsValid() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(EMAIL, PASSWORD))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
        .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
        .andExpect(jsonPath("$.email", equalTo(EMAIL)))
        .andExpect(status().isCreated());

    assertUserHasBeenCreated(USER_EMAIL);
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsAlreadyBeingUsed() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(USER_EMAIL, PASSWORD))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Email is being used, try another!")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenCredentialsHaveInvalidFormat() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("wrongEmailFormat", PASSWORD))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasItems("Email should be valid.")))
        .andExpect(status().isBadRequest());
  }

  private void assertUserHasBeenCreated(String email) {
    Optional<UserEntity> userEntity = userRepository.findByEmail(email);
    assertTrue(userEntity.isPresent());
    assertThat(userEntity.get().isSoftDeleted()).isFalse();
    userRepository.delete(userEntity.get());
  }

  private String buildRequest(String email, String password) throws JsonProcessingException {
    return objectMapper.writeValueAsString(buildUserRegisterRequest(email, password));
  }

  private static UserRegisterRequest buildUserRegisterRequest(String email, String password) {
    UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
    userRegisterRequest.setFirstName(FIRST_NAME);
    userRegisterRequest.setLastName(LAST_NAME);
    userRegisterRequest.setEmail(email);
    userRegisterRequest.setPassword(password);
    return userRegisterRequest;
  }

}
