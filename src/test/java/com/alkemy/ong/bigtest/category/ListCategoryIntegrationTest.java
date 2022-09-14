package com.alkemy.ong.bigtest.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasItem;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ListCategoryIntegrationTest extends BigTest {

  private static final String URL = "/categories";

  @Test
  public void shouldListWithNewsCategoryWhenUserHasAdminRole() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.categories", hasSize(1)))
        .andExpect(jsonPath("$.categories[0].name", equalTo("News")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldListCategoriesWhenUserHasAdminRole() throws Exception {
    String categoryName = getRandomCategory().getName();

    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.categories", hasSize(2)))
        .andExpect(jsonPath("$.categories[0].name", equalTo("News")))
        .andExpect(jsonPath("$.categories[1].name", equalTo(categoryName)))
        .andExpect(status().isOk());

  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(get(URL, String.valueOf(randomCategoryId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }
}