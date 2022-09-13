package com.alkemy.ong.bigtest.category;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.rest.request.category.UpdateCategoryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateCategoryIntegrationTest extends BigTest {

  private static final String URL = "/categories/{id}";

  @Test
  public void shouldUpdateCategoryWhenUserHasAdminRole() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(put(URL, String.valueOf(randomCategoryId))
            .content(getDefaultRequestBody())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo("Soccer")))
        .andExpect(jsonPath("$.description", equalTo("Soccer description")))
        .andExpect(jsonPath("$.imageUrl", equalTo("https://s3.com/soccer.jpg")))
        .andExpect(status().isOk());

    assertCategoryHasBeenUpdated(randomCategoryId);
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(put(URL, "1")
            .content(getDefaultRequestBody())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnNotFoundWhenCategoryDoesNotExist() throws Exception {
    String nonExistentUserId = "999";

    mockMvc.perform(put(URL, nonExistentUserId)
            .content(getDefaultRequestBody())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Category not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsNull() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(put(URL, String.valueOf(randomCategoryId))
            .content(buildRequest(null,
                "Tennis description",
                "https://s3.com/tennis.jpg"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());

    deleteCategory(randomCategoryId);
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsNumbers() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(put(URL, String.valueOf(randomCategoryId))
            .content(buildRequest("T3nn1s",
                "Tennis description",
                "https://s3.com/tennis.jpg"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());

    deleteCategory(randomCategoryId);
  }

  private String getDefaultRequestBody() throws JsonProcessingException {
    return buildRequest("Soccer",
        "Soccer description",
        "https://s3.com/soccer.jpg");
  }

  private String buildRequest(String name, String description, String imageUrl)
      throws JsonProcessingException {
    UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();
    updateCategoryRequest.setName(name);
    updateCategoryRequest.setDescription(description);
    updateCategoryRequest.setImageUrl(imageUrl);
    return convert(updateCategoryRequest);
  }

  private void assertCategoryHasBeenUpdated(Long categoryId) {
    Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
    assertTrue(categoryEntity.isPresent());
    assertEquals("Soccer", categoryEntity.get().getName());
    assertEquals("Soccer description", categoryEntity.get().getDescription());
    assertEquals("https://s3.com/soccer.jpg", categoryEntity.get().getImageUrl());
    deleteCategory(categoryId);
  }

}
