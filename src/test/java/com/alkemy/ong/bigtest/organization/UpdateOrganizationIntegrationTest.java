package com.alkemy.ong.bigtest.organization;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.builder.UpdateOrganizationRequestBuilder;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateOrganizationIntegrationTest extends BigTest {

  private final String URL = "/organization/public";

  @Test
  public void shouldUpdateOrganizationWhenUserHasAdminRole() throws Exception {
    mockMvc.perform(patch(URL)
            .content(buildDefaultRequest())
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", equalTo("somos mas")))
        .andExpect(jsonPath("$.imageUrl", equalTo("https://s3.com/updatedlogo.jpg/")))
        .andExpect(jsonPath("$.address", equalTo("Elm Street 30")))
        .andExpect(jsonPath("$.phone", equalTo("+540303111")))
        .andExpect(jsonPath("$.email", equalTo("somos.mas@ong.com.ar")))
        .andExpect(jsonPath("$.socialMedia", notNullValue()))
        .andExpect(
            jsonPath("$.socialMedia.facebookUrl", equalTo("https://www.facebook.com/somos_mas/")))
        .andExpect(jsonPath("$.socialMedia.linkedInUrl",
            equalTo("https://www.linkedin.com/in/somos_mas/")))
        .andExpect(
            jsonPath("$.socialMedia.instagramUrl", equalTo("https://www.instagram.com/SOMOSMAS/")))
        .andExpect(jsonPath("$.aboutUsText", equalTo("About ...")))
        .andExpect(jsonPath("$.welcomeText", equalTo("Welcome to Somos mas")))
        .andExpect(status().isOk());

    assertOrganizationHasBeenUpdated();
  }

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(patch(URL)
            .content(buildDefaultRequest())
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsNumbers() throws Exception {
    mockMvc.perform(patch(URL)
            .content(buildRequest(
                "123",
                "somos.mas@ong.com.ar",
                "welcome"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenEmailHasInvalidFormat() throws Exception {
    mockMvc.perform(patch(URL)
            .content(buildRequest(
                "somos mas",
                "somos mas",
                "welcome"))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Email has invalid format.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenWelcomeTextHasMoreThanAllowedCharacters()
      throws Exception {
    mockMvc.perform(patch(URL)
            .content(buildRequest(
                "somos mas",
                "somos.mas@ong.com.ar",
                "a".repeat(51)))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo",
            hasItem("Welcome text length must be shorter than 50 characters.")))
        .andExpect(status().isBadRequest());
  }

  private void assertOrganizationHasBeenUpdated() {
    OrganizationEntity organizationEntity = organizationRepository.findAll().get(0);
    assertEquals("somos mas", organizationEntity.getName());
    assertEquals("https://s3.com/updatedlogo.jpg/", organizationEntity.getImageUrl());
    assertEquals("Elm Street 30", organizationEntity.getAddress());
    assertEquals("+540303111", organizationEntity.getPhone());
    assertEquals("somos.mas@ong.com.ar", organizationEntity.getEmail());
    assertEquals("https://www.facebook.com/somos_mas/", organizationEntity.getFacebookUrl());
    assertEquals("https://www.linkedin.com/in/somos_mas/", organizationEntity.getLinkedInUrl());
    assertEquals("https://www.instagram.com/SOMOSMAS/", organizationEntity.getInstagramUrl());
    assertEquals("About ...", organizationEntity.getAboutUsText());
    assertEquals("Welcome to Somos mas", organizationEntity.getWelcomeText());
  }

  private String buildDefaultRequest() throws JsonProcessingException {
    return convert(UpdateOrganizationRequestBuilder.defaultRequest());
  }

  private String buildRequest(String name, String email, String welcomeText)
      throws JsonProcessingException {
    return convert(UpdateOrganizationRequestBuilder.buildRequest(name, email, welcomeText));
  }

}