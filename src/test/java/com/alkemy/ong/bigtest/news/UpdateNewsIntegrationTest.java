package com.alkemy.ong.bigtest.news;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.rest.request.news.UpdateNewsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateNewsIntegrationTest extends BigTest {

  private static final String URL = "/news/{id}";
  private static final String NEWS_NAME = "Name with three spaces";
  private static final String NEWS_CONTENT = "Text with 1 number";
  private static final String NEWS_IMAGE = "image.jpg";

  @Test
  public void shouldUpdateNewsWhenUserHasAdminRole() throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(getUpdateRequest(NEWS_NAME, NEWS_CONTENT))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo(NEWS_NAME)))
        .andExpect(jsonPath("$.content", equalTo(NEWS_CONTENT)))
        .andExpect(jsonPath("$.imageUrl", equalTo(NEWS_IMAGE)))
        .andExpect(jsonPath("$.category", nullValue()))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(getUpdateRequest(
                "",
                NEWS_CONTENT
            ))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasMoreThanAllowedCharacters()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(getUpdateRequest(
                "aNewsNameContainingMoreThanFiftyCharactersConsecutively",
                NEWS_CONTENT
            ))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must be 50 characters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenContentIsEmpty()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(getUpdateRequest(
                NEWS_NAME,
                ""
            ))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content must be alphanumeric.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenContentHasASymbol()
      throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(getUpdateRequest(
                NEWS_NAME,
                "Content with 1 symbol!"
            ))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Content must be alphanumeric.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    Long randomNewsId = createNews("News").getNewsId();

    mockMvc.perform(put(URL, String.valueOf(randomNewsId))
            .content(getUpdateRequest(NEWS_NAME, NEWS_CONTENT))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  String getUpdateRequest(String name, String content)
      throws JsonProcessingException {
    return convert(buildUpdateNewsRequest(name, content));
  }

  private UpdateNewsRequest buildUpdateNewsRequest(String name, String content) {
    UpdateNewsRequest updateNewsRequest = new UpdateNewsRequest();
    updateNewsRequest.setName(name);
    updateNewsRequest.setContent(content);
    updateNewsRequest.setImage(UpdateNewsIntegrationTest.NEWS_IMAGE);
    return updateNewsRequest;
  }
}
