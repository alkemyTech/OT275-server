package com.alkemy.ong.bigtest.news;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.rest.request.news.CreateNewsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class AddNewsIntegrationTest extends BigTest {

  private static final String URL = "/news";
  private static final String NEWS_NAME = "Name with three spaces";
  private static final String NEWS_TEXT = "Text with 1 number";
  private static final String NEWS_IMAGE = "1NewsImageWithoutWhiteSpaces.jpg";

  @Test
  public void shouldCreateNewsWhenUserHasAdminRole() throws Exception {
    String result = mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(NEWS_NAME, NEWS_TEXT, NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo(NEWS_NAME)))
        .andExpect(jsonPath("$.content", equalTo(NEWS_TEXT)))
        .andExpect(jsonPath("$.imageUrl", equalTo(NEWS_IMAGE)))
        .andExpect(jsonPath("$.category.description", equalTo("News description")))
        .andExpect(jsonPath("$.category.imageUrl", equalTo("")))
        .andExpect(jsonPath("$.category.name", equalTo("News")))
        .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

    Integer newsId = JsonPath.read(result, "$.id");
    assertNewsHasBeenCreated(Long.valueOf(newsId));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest("",NEWS_TEXT,NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasANumber() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest("Name with 1 number",NEWS_TEXT,NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenHasMoreThanFiftyCharacters() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(
                "aNewsNameContainingMoreThanFiftyCharactersConsecutively",
                NEWS_TEXT,
                NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must be 50 characters or less.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenTextIsEmpty() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(NEWS_NAME,"",NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Text cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Text must be alphanumeric.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenTextHasASymbol() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(NEWS_NAME,"Text with 1 symbol!",NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Text must be alphanumeric.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageIsEmpty() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(NEWS_NAME,NEWS_TEXT,"")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Image cannot be empty.")))
        .andExpect(jsonPath("$.moreInfo", hasItem("Image must be alphanumeric without white spaces.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenImageHasSpaces() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(NEWS_NAME,NEWS_TEXT,"News Image with 4 spaces")))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Image must be alphanumeric without white spaces.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(buildCreateNewsRequest(NEWS_NAME,NEWS_TEXT,NEWS_IMAGE)))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  private String buildRequest(CreateNewsRequest newsRequest)
      throws JsonProcessingException {
    return convert(newsRequest);
  }

  private CreateNewsRequest buildCreateNewsRequest(String name, String text, String image) {
    CreateNewsRequest request = new CreateNewsRequest();
    request.setName(name);
    request.setText(text);
    request.setImage(image);
    return request;
  }

  private void assertNewsHasBeenCreated(Long newsId) {
    Optional<NewsEntity> newsEntity = newsRepository.findById(newsId);
    assertTrue(newsEntity.isPresent());
    assertEquals(NEWS_NAME, newsEntity.get().getName());
    assertEquals(NEWS_TEXT, newsEntity.get().getContent());
    assertEquals(NEWS_IMAGE, newsEntity.get().getImageUrl());
    assertThat(newsEntity.get().isSoftDeleted()).isFalse();
  }
}
