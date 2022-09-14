package com.alkemy.ong.bigtest.category;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetCategoryIntegrationTest extends BigTest {

  private static final String URL = "/categories/{id}";

  @Test
  public void shouldGetCategoryWhenUserHasAdminRole() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(get(URL, String.valueOf(randomCategoryId))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.description", equalTo("Sports description")))
        .andExpect(jsonPath("$.imageUrl", equalTo("https://s3.com/sports-category.jpg")))
        .andExpect(jsonPath("$.name", equalTo("Sports")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNotFoundWhenCategoryDoesNotExist() throws Exception {
    String nonExistingCategoryId = "271827";

    mockMvc.perform(get(URL, nonExistingCategoryId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", IsEqual.equalTo(404)))
        .andExpect(jsonPath("$.message", IsEqual.equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Category not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(get(URL, String.valueOf(randomCategoryId))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", IsEqual.equalTo(403)))
        .andExpect(jsonPath("$.message", IsEqual.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }
}
