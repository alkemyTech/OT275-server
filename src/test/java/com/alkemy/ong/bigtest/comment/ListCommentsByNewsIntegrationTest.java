package com.alkemy.ong.bigtest.comment;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ListCommentsByNewsIntegrationTest extends BigTest {

  private static final String URL = "/news/{id}/comments";


  @Test
  public void shouldListCommentsWhenNewsExistAndUserHasStandardRole() throws Exception {
    Long newsId = createNewsWithRandomComment("Halloween").getNewsId();

    mockMvc.perform(get(URL, String.valueOf(newsId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.name", equalTo("Halloween")))
        .andExpect(jsonPath("$.comments", hasSize(1)))
        .andExpect(jsonPath("$.comments[*].body").value(hasItem("Awesome post!")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnEmptyListOfCommentsWhenNewsExistAndUserHasStandardRole()
      throws Exception {
    Long newsId = createNews("Halloween").getNewsId();

    mockMvc.perform(get(URL, String.valueOf(newsId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.name", equalTo("Halloween")))
        .andExpect(jsonPath("$.comments").value(empty()))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNotFoundWhenNewsDoesNotExistAndUserHasStandardRole() throws Exception {
    mockMvc.perform(get(URL, "9999")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("News not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasAdminRole() throws Exception {
    mockMvc.perform(get(URL, "1")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

}
