package com.alkemy.ong.bigtest.category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class DeleteCategoryIntegrationTest extends BigTest {

  private static final String URL = "/categories/{id}";

  @Test
  public void shouldDeleteCategoryWhenUserHasAdminRole() throws Exception {
    Long randomCategoryId = getRandomCategoryId();

    mockMvc.perform(delete(URL, String.valueOf(randomCategoryId))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isNoContent());

    assertCategoryHasBeenDeleted(randomCategoryId);
  }

  @Test
  public void shouldReturnNotFoundWhenCategoryDoesNotExists() throws Exception {
    mockMvc.perform(delete(URL, "314")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Category not found.")))
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

  private void assertCategoryHasBeenDeleted(Long randomCategoryId) {
    Optional<CategoryEntity> category = categoryRepository.findById(randomCategoryId);
    assertTrue(category.isPresent());
    assertTrue(category.get().isSoftDeleted());
  }
}
