package com.alkemy.ong.bigtest.news;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ListNewsIntegrationTest extends BigTest {

  private static final String URL = "/news";

  @Test
  public void shouldListNewsWhenUserHasAdminRole() throws Exception {
    for (int i = 1; i < 15; i++) {
      createNews("News number " + i);
    }
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.page", equalTo(0)))
        .andExpect(jsonPath("$.size", equalTo(10)))
        .andExpect(jsonPath("$.totalPages", equalTo(2)))
        .andExpect(jsonPath("$.news", hasSize(10)))
        .andExpect(jsonPath("$.news[0].id", notNullValue()))
        .andExpect(jsonPath("$.news[0].name", equalTo("News number 1")))
        .andExpect(jsonPath("$.news[0].content", equalTo("Content for News number 1")))
        .andExpect(jsonPath("$.news[0].imageUrl", equalTo("")))
        .andExpect(jsonPath("$.news[0].category.id", notNullValue()))
        .andExpect(jsonPath("$.news[0].category.description", equalTo("News description")))
        .andExpect(jsonPath("$.news[0].category.imageUrl", equalTo("")))
        .andExpect(jsonPath("$.news[0].category.name", equalTo("News")))
        .andExpect(header().string(LINK_HEADER, equalTo(
            "<http://localhost/news?page=1&size=10>; rel=\"last\"<http://localhost/news?page=1&size=10>; rel=\"next\"")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldListNewsWhenUserHasStandard() throws Exception {
    for (int i = 1; i < 15; i++) {
      createNews("News number " + i);
    }
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.page", equalTo(0)))
        .andExpect(jsonPath("$.size", equalTo(10)))
        .andExpect(jsonPath("$.totalPages", equalTo(2)))
        .andExpect(jsonPath("$.news", hasSize(10)))
        .andExpect(jsonPath("$.news[0].id", notNullValue()))
        .andExpect(jsonPath("$.news[0].name", equalTo("News number 1")))
        .andExpect(jsonPath("$.news[0].content", equalTo("Content for News number 1")))
        .andExpect(jsonPath("$.news[0].imageUrl", equalTo("")))
        .andExpect(jsonPath("$.news[0].category.id", notNullValue()))
        .andExpect(jsonPath("$.news[0].category.description", equalTo("News description")))
        .andExpect(jsonPath("$.news[0].category.imageUrl", equalTo("")))
        .andExpect(jsonPath("$.news[0].category.name", equalTo("News")))
        .andExpect(header().string(LINK_HEADER, equalTo(
            "<http://localhost/news?page=1&size=10>; rel=\"last\"<http://localhost/news?page=1&size=10>; rel=\"next\"")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnEmptyListWhenThereAreNoNews() throws Exception {

    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.page", equalTo(0)))
        .andExpect(jsonPath("$.size", equalTo(10)))
        .andExpect(jsonPath("$.totalPages", equalTo(0)))
        .andExpect(jsonPath("$.news").value(empty()))
        .andExpect(header().string(LINK_HEADER, emptyString()))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnForbiddenWhenNoCredentialsAreProvided() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, ""))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }
}
