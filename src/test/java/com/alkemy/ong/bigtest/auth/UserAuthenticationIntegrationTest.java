package com.alkemy.ong.bigtest.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.rest.request.user.AuthenticationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;


public class UserAuthenticationIntegrationTest extends BigTest {

  private static final String URL = "/auth/login";

  @Test
  public void shouldReturnTokenWhenCredentialsAreValid() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("jason@voorhees.com", "abcd1234"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.token", notNullValue()))
        .andExpect(jsonPath("$.firstName", equalTo("Jason")))
        .andExpect(jsonPath("$.lastName", equalTo("Voorhees")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnIsForbiddenStatusCodeWhenCredentialsAreInvalid() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("robert@martin.com", "wrongPassword"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo("Invalid credentials.")))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Invalid username or password.")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnBadRequestWhenCredentialsHaveInvalidFormat() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("wrongEmailFormat", "wrongPasswordFormat"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Password must be between 8 and 16 characters long.",
                "Email has invalid format.")))
        .andExpect(status().isBadRequest());
  }

  private String buildRequest(String email, String password) throws JsonProcessingException {
    return convert(new AuthenticationRequest(email, password));
  }

}
