package com.alkemy.ong.bigtest.activity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.CreateActivityRequestBuilder;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateActivityIntegrationTest extends BigTest {

  private static final String URL = "/activities";
  private static final String NAME = "My Activity";
  private static final String CONTENT = "My Activity content";
  private static final String IMAGE = "https://s3.com/default-image.jpg";

  @Test
  public void shouldCreateActivityWhenUserHasAdminRole() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, CONTENT, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.content", equalTo(CONTENT)))
        .andExpect(jsonPath("$.imageUrl", equalTo(IMAGE)))
        .andExpect(status().isCreated());
    assertActivityHasBeenCreated(NAME);
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, CONTENT, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasMoreThanAllowedCharacters() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("x".repeat(21), CONTENT, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must be between 3 and 20 characters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasLessThanAllowedCharacters() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("xx", CONTENT, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must be between 3 and 20 characters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsNull() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(null, CONTENT, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsNumbers() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME + "1234", CONTENT, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsNull() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, null, IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content must be alphanumeric.")))
        .andExpect(status().isBadRequest());
  }

  private void assertActivityHasBeenCreated(String name) {
    Optional<ActivityEntity> activityEntity = activityRepository.findByName(name);
    assertTrue(activityEntity.isPresent());
    assertThat(activityEntity.get().isSoftDeleted()).isFalse();
    activityRepository.delete(activityEntity.get());
  }

  private String buildRequest(String name, String content, String imageUrl)
      throws JsonProcessingException {
    return convert(CreateActivityRequestBuilder.buildRequest(name, content, imageUrl));
  }

}
