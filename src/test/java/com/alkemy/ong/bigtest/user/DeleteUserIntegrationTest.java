package com.alkemy.ong.bigtest.user;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DeleteUserIntegrationTest extends BigTest {

  private static final String URL = "/users/{id}";

  @Test
  public void shouldDeleteUserWhenUserHasAdminRole() throws Exception {
    UserEntity randomUser = getRandomUser();

    mockMvc.perform(delete(URL, String.valueOf(randomUser.getUserId()))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isNoContent());

    assertUserHasBeenDeleted(randomUser.getUserId());

    cleanUsersData(randomUser);
  }

  @Test
  public void shouldDeleteUserWhenUserHasStandardRole() throws Exception {
    UserEntity randomUser = getRandomUser();

    mockMvc.perform(delete(URL, String.valueOf(randomUser.getUserId()))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(status().isNoContent());

    assertUserHasBeenDeleted(randomUser.getUserId());

    cleanUsersData(randomUser);
  }

  @Test
  public void shouldReturnForbiddenWhenTokenIsInvalid() throws Exception {
    mockMvc.perform(delete(URL, "1")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, INVALID_TOKEN))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
    String nonExistentUserId = "1000000";
    mockMvc.perform(delete(URL, nonExistentUserId)
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("User not found.")))
        .andExpect(status().isNotFound());
  }

  private void assertUserHasBeenDeleted(Long userId) {
    Optional<UserEntity> deletedUser = userRepository.findById(userId);
    deletedUser.ifPresent(userEntity -> assertFalse(userEntity.isEnabled()));
    deletedUser.ifPresent(userEntity -> assertTrue(userEntity.isSoftDeleted()));
  }

}