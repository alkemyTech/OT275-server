package com.alkemy.ong.bigtest.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetUserAuthenticationDetailsIntegrationTest extends BigTest {

  private static final String URL = "/auth/me";

  @Test
  public void shouldReturn403WhenAuthenticationTokenIsNotValid() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(
            "Access denied.")))
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
