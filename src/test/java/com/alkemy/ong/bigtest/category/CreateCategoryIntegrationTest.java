package com.alkemy.ong.bigtest.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.rest.request.category.CreateCategoryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateCategoryIntegrationTest extends BigTest {

  private static final String URL = "/categories";
  private static final String CATEGORY_NAME = "Category Name";
  private static final String CATEGORY_DESCRIPTION = "Category description";
  private static final String CATEGORY_IMAGE = "https://s3.com/image.jpg";

  @Test
  public void shouldCreateCategoryWhenUserHasAdminRole() throws Exception {
    String result = mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateCategoryRequest(CATEGORY_NAME)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo(CATEGORY_NAME)))
        .andExpect(jsonPath("$.description", equalTo(CATEGORY_DESCRIPTION)))
        .andExpect(jsonPath("$.image", equalTo(CATEGORY_IMAGE)))
        .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

    Integer categoryId = JsonPath.read(result, "$.id");
    assertCategoryHasBeenCreated(Long.valueOf(categoryId));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsNull() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateCategoryRequest(null)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name cannot be empty.")))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasNumbers() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateCategoryRequest("Category Name 2")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateCategoryRequest(CATEGORY_NAME)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  private CreateCategoryRequest buildCreateCategoryRequest(String categoryName) {
    CreateCategoryRequest request = new CreateCategoryRequest();
    request.setName(categoryName);
    request.setDescription(CATEGORY_DESCRIPTION);
    request.setImage(CATEGORY_IMAGE);
    return request;
  }

  private String buildRequest(CreateCategoryRequest categoryRequest)
      throws JsonProcessingException {
    return convert(categoryRequest);
  }

  private void assertCategoryHasBeenCreated(Long categoryId) {
    Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
    assertTrue(categoryEntity.isPresent());
    assertEquals(CATEGORY_NAME, categoryEntity.get().getName());
    assertEquals(CATEGORY_DESCRIPTION, categoryEntity.get().getDescription());
    assertEquals(CATEGORY_IMAGE, categoryEntity.get().getImageUrl());
    assertThat(categoryEntity.get().isSoftDeleted()).isFalse();
  }

}
