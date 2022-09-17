package com.alkemy.ong.bigtest.user;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.UpdateUserRequestBuilder;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UpdateUserIntegrationTest extends BigTest {

  @Autowired
  private PasswordEncoder passwordEncoder;


  private static final String URL = "/users/{id}";

  @Test
  public void shouldUpdateUserWhenHasAdminRole() throws Exception {
    Long randomUserId = getRandomUserId();

    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildDefaultRequest())
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.firstName", equalTo("Mariano")))
        .andExpect(jsonPath("$.lastName", equalTo("Toranzo")))
        .andExpect(jsonPath("$.imageUrl", equalTo("https://s3.com/default-image.jpg")))
        .andExpect(status().isOk());
    assertUserHasBeenUpdated(randomUserId);
  }

  @Test
  public void shouldUpdateUserWhenHasUserRole() throws Exception {
    Long randomUserId = getRandomUserId();

    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildDefaultRequest())
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.firstName", equalTo("Mariano")))
        .andExpect(jsonPath("$.lastName", equalTo("Toranzo")))
        .andExpect(jsonPath("$.imageUrl", equalTo("https://s3.com/default-image.jpg")))
        .andExpect(status().isOk());
    assertUserHasBeenUpdated(randomUserId);
  }

  @Test
  public void shouldUpdateWhenUserHasStandardRole() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildDefaultRequest())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
    String nonExistentUserId = "999";
    mockMvc.perform(put(URL, nonExistentUserId)
        .content(buildDefaultRequest())
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("User not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnBadRequestWhenFirstnameIsNull() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            null,
            "Toranzo",
            "https://s3.com/default-image.jpg",
            "kdiiei18"))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("First name cannot be empty.")))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("First name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenLastNameIsNull() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            "Mariano",
            null,
            "https://s3.com/default-image.jpg",
            "kdiiei18"))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Last name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenFirstNameContainsNumbers() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            "You name is 333",
            "You lastname",
            "https://s3.com/default-image.jpg",
            "kdiiei18"))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("First name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenLastNameContainsNumbers() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            "Mariano",
            "You lastname 8754",
            "https://s3.com/default-image.jpg",
            "kdiiei18"))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Last name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenPasswordIsNull() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            "Mariano",
            "Toranzo",
            "https://s3.com/default-image.jpg",
            null))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Password cannot be empty")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenPasswordIsLessThanEight() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            "Mariano",
            "Toranzo",
            "https://s3.com/default-image.jpg",
            "dalib"))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Password must be between 8 and 16 characters long.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenPasswordIsGreaterThanSixteen() throws Exception {
    Long randomUserId = getRandomUserId();
    mockMvc.perform(put(URL, String.valueOf(randomUserId))
        .content(buildRequest(
            "Mariano",
            "Toranzo",
            "https://s3.com/default-image.jpg",
            "dalibkieuywpqppisnel"))
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Password must be between 8 and 16 characters long.")))
        .andExpect(status().isBadRequest());
  }

  private void assertUserHasBeenUpdated(Long userId) {
    Optional<UserEntity> userEntity = userRepository.findById(userId);
    assertTrue(userEntity.isPresent());
    assertEquals("Mariano", userEntity.get().getFirstName());
    assertEquals("Toranzo", userEntity.get().getLastName());
    assertEquals("https://s3.com/default-image.jpg", userEntity.get().getImageUrl());
    assertTrue(passwordEncoder.matches("abcd12345", userEntity.get().getPassword()));
  }

  private String buildDefaultRequest() throws JsonProcessingException {
    return convert(UpdateUserRequestBuilder.defaultRequest());
  }

  private String buildRequest(String firstname, String lastname, String imageUrl, String password)
      throws JsonProcessingException {
    return convert(
        UpdateUserRequestBuilder.buildRequest(firstname, lastname, imageUrl, password));
  }
}
