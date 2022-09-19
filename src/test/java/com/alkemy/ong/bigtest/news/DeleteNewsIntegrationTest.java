package com.alkemy.ong.bigtest.news;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class DeleteNewsIntegrationTest extends BigTest {

  private static final String URL = "/news/{id}";


  @Test
  public void shouldDeleteNewsWhenUserHasAdminRole() throws Exception {
    Long randomNewsId = createNews("News Name").getNewsId();

    mockMvc.perform(delete(URL, String.valueOf(randomNewsId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isNoContent());

    assertNewsHasBeenDeleted(randomNewsId);
  }

  @Test
  public void shouldReturnNotFoundWhenNewsDoesNotExists() throws Exception {
    mockMvc.perform(delete(URL, "1380")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("News not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(delete(URL, "1")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  private void assertNewsHasBeenDeleted(Long randomNewsId) {
    Optional<NewsEntity> news = newsRepository.findById(randomNewsId);
    assertTrue(news.isPresent());
    assertTrue(news.get().isSoftDeleted());
  }
}
