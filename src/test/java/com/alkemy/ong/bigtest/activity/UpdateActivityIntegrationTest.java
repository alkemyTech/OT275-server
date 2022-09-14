package com.alkemy.ong.bigtest.activity;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.UpdateActivityRequestBuilder;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateActivityIntegrationTest extends BigTest {

  private static final String URL = "/activities/{id}";

  @Test
  public void shouldUpdateActivityWhenUserHasAdminRole() throws Exception {
    Long randomActivityId = getRandomActivityId();
    mockMvc.perform(put(URL, String.valueOf(randomActivityId))
            .content(buildDefaultRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo("Updated Activity")))
        .andExpect(jsonPath("$.content", equalTo("Updated Activity content")))
        .andExpect(jsonPath("$.imageUrl", equalTo("https://s3.com/default-image.jpg")))
        .andExpect(status().isOk());
    assertActivityHasBeenUpdated(randomActivityId);
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(put(URL, "1")
            .content(buildDefaultRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnNotFoundWhenActivityDoesNotExist() throws Exception {
    String nonExistentActivityId = "999";
    mockMvc.perform(put(URL, nonExistentActivityId)
            .content(buildDefaultRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Activity not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsNull() throws Exception {
    Long randomActivityId = getRandomActivityId();
    mockMvc.perform(put(URL, String.valueOf(randomActivityId))
            .content(buildRequest(null,
                "My Activity content",
                "https://s3.com/default-image.jpg"))
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
    Long randomActivityId = getRandomActivityId();
    mockMvc.perform(put(URL, String.valueOf(randomActivityId))
            .content(buildRequest("My Activity 999",
                "My Activity content",
                "https://s3.com/default-image.jpg"))
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
    Long randomActivityId = getRandomActivityId();
    mockMvc.perform(put(URL, String.valueOf(randomActivityId))
            .content(buildRequest("My Activity",
                null,
                "https://s3.com/default-image.jpg"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenContentHasNumbers() throws Exception {
    Long randomActivityId = getRandomActivityId();
    mockMvc.perform(put(URL, String.valueOf(randomActivityId))
            .content(buildRequest("My Activity",
                "My Activity content 1234",
                "https://s3.com/default-image.jpg"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsNull() throws Exception {
    Long randomActivityId = getRandomActivityId();
    mockMvc.perform(put(URL, String.valueOf(randomActivityId))
            .content(buildRequest("My Activity",
                "My Activity content",
                null))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Image must be an URL.")))
        .andExpect(status().isBadRequest());
  }

  private void assertActivityHasBeenUpdated(Long activityId) {
    Optional<ActivityEntity> activityEntity = activityRepository.findById(activityId);
    assertTrue(activityEntity.isPresent());
    assertEquals("Updated Activity", activityEntity.get().getName());
    assertEquals("Updated Activity content", activityEntity.get().getContent());
    assertEquals("https://s3.com/default-image.jpg", activityEntity.get().getImageUrl());
  }

  private String buildDefaultRequest() throws JsonProcessingException {
    return convert(UpdateActivityRequestBuilder.defaultRequest());
  }

  private String buildRequest(String name, String content, String imageUrl)
      throws JsonProcessingException {
    return convert(UpdateActivityRequestBuilder.buildRequest(name, content, imageUrl));
  }

}
