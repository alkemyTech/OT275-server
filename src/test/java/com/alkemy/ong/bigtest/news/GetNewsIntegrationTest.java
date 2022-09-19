package com.alkemy.ong.bigtest.news;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import org.junit.Test;
import org.springframework.http.HttpHeaders;


public class GetNewsIntegrationTest extends BigTest {

  private static final String URL = "/news/{id}";

  @Test
  public void shouldReturnNewsWhenUserHasAdminRole() throws Exception {
    NewsEntity news = createNews("newsName");
    mockMvc.perform(get(URL, String.valueOf(news.getNewsId()))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo(news.getName())))
        .andExpect(jsonPath("$.content", equalTo(news.getContent())))
        .andExpect(jsonPath("$.imageUrl", equalTo(news.getImageUrl())))
        .andExpect(jsonPath("$.category.id", notNullValue()))
        .andExpect(jsonPath("$.category.description", equalTo(news.getCategory().getDescription())))
        .andExpect(jsonPath("$.category.imageUrl", equalTo(news.getCategory().getImageUrl())))
        .andExpect(jsonPath("$.category.name", equalTo(news.getCategory().getName())))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNewsWhenUserHasStandardRole() throws Exception {
    NewsEntity news = createNews("newsName");
    mockMvc.perform(get(URL, String.valueOf(news.getNewsId()))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo(news.getName())))
        .andExpect(jsonPath("$.content", equalTo(news.getContent())))
        .andExpect(jsonPath("$.imageUrl", equalTo(news.getImageUrl())))
        .andExpect(jsonPath("$.category.id", notNullValue()))
        .andExpect(jsonPath("$.category.description", equalTo(news.getCategory().getDescription())))
        .andExpect(jsonPath("$.category.imageUrl", equalTo(news.getCategory().getImageUrl())))
        .andExpect(jsonPath("$.category.name", equalTo(news.getCategory().getName())))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNotFoundWhenNewsDoesNotExist() throws Exception {
    String nonExistingNewsId = "6022";

    mockMvc.perform(get(URL, nonExistingNewsId)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("News not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnForbiddenNoCredentialsAreProvided() throws Exception {
    mockMvc.perform(get(URL, "1")
            .header(HttpHeaders.AUTHORIZATION, ""))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }
}
