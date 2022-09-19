package com.alkemy.ong.bigtest.user;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class ListUserIntegrationTest extends BigTest {

  private static final String URL = "/users";

  @Test
  public void shouldListUsersWhenUserHasAdminRole() throws Exception {

    mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.users", hasSize(2)))
        .andExpect(jsonPath("$.users[0]",hasKey("id")))
        .andExpect(jsonPath("$.users[0]",hasKey("firstName")))
        .andExpect(jsonPath("$.users[0]",hasKey("lastName")))
        .andExpect(jsonPath("$.users[0]",hasKey("email")))
        .andExpect(jsonPath("$.users[0]",hasKey("role")))
        .andExpect(status().isOk());
    }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(get(URL)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }
}
