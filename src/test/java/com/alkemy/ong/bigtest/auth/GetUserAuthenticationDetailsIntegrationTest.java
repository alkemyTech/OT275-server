package com.alkemy.ong.bigtest.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetUserAuthenticationDetailsIntegrationTest extends BigTest {

  private static final String URL = "/auth/me";

  @Test
  public void shouldReturnForbiddenWhenAuthenticationTokenIsNotValid() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(jsonPath("$.statusCode", IsEqual.equalTo(403)))
        .andExpect(jsonPath("$.message", IsEqual.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnUserInformationWhenRequestHasValidToken() throws Exception {
    mockMvc.perform(get(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.email", equalTo("freedy@krueger.com")))
        .andExpect(jsonPath("$.firstName", equalTo("Freddy")))
        .andExpect(jsonPath("$.lastName", equalTo("Krueger")))
        .andExpect(jsonPath("$.imageUrl", equalTo(null)))
        .andExpect(status().isOk());
  }
}
