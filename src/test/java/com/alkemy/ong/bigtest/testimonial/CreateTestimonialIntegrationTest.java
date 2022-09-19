package com.alkemy.ong.bigtest.testimonial;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import com.alkemy.ong.infrastructure.rest.request.testimonial.CreateTestimonialRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateTestimonialIntegrationTest extends BigTest {

  private static final String URL = "/testimonials";
  private static final String TESTIMONIAL_NAME = "testimonial name";
  private static final String TESTIMONIAL_CONTENT = "testimonial content";
  private static final String TESTIMONIAL_IMAGE = "testimonial-image.jpg";


  @Test
  public void shouldCreateTestimonialWhenUserHasAdminRole() throws Exception {
    String testimonialResponse =
        mockMvc.perform(post(URL)
                .content(buildRequest(TESTIMONIAL_NAME, TESTIMONIAL_CONTENT, TESTIMONIAL_IMAGE))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo(TESTIMONIAL_NAME)))
            .andExpect(jsonPath("$.content", equalTo(TESTIMONIAL_CONTENT)))
            .andExpect(jsonPath("$.imageUrl", equalTo(TESTIMONIAL_IMAGE)))
            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    Integer testimonialId = JsonPath.read(testimonialResponse, "$.id");
    assertTestimonialHasBeenCreated(Long.valueOf(testimonialId));
  }

  @Test
  public void shouldCreateTestimonialWhenUserHasStandardRole() throws Exception {
    String testimonialResponse =
        mockMvc.perform(post(URL)
                .content(buildRequest(TESTIMONIAL_NAME, TESTIMONIAL_CONTENT, TESTIMONIAL_IMAGE))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo(TESTIMONIAL_NAME)))
            .andExpect(jsonPath("$.content", equalTo(TESTIMONIAL_CONTENT)))
            .andExpect(jsonPath("$.imageUrl", equalTo(TESTIMONIAL_IMAGE)))
            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    Integer testimonialId = JsonPath.read(testimonialResponse, "$.id");
    assertTestimonialHasBeenCreated(Long.valueOf(testimonialId));
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("", TESTIMONIAL_CONTENT, TESTIMONIAL_NAME))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItems(
            "Name must not be empty.",
            "Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsNotAllowedCharacters()
      throws Exception {

    mockMvc.perform(post(URL)
            .content(buildRequest("T3st1m0n14l", TESTIMONIAL_CONTENT, TESTIMONIAL_NAME))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenNameSizeIsGreaterThanAllowed()
      throws Exception {
    String nameTooLong = RandomStringUtils.random(51, "a");
    mockMvc.perform(post(URL)
            .content(buildRequest(
                nameTooLong,
                TESTIMONIAL_CONTENT,
                TESTIMONIAL_NAME))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(
            "Name must shorter than 50 characters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenContentDoesNotContainsAllowedCharacters()
      throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(
                TESTIMONIAL_NAME,
                "T€$timoni@l",
                TESTIMONIAL_NAME))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(
            "Content must be alphanumeric.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenContentSizeIsGreaterThanAllowed()
      throws Exception {

    String contentTooLong = RandomStringUtils.random(151, "a");

    mockMvc.perform(post(URL)
            .content(buildRequest(
                TESTIMONIAL_NAME,
                contentTooLong,
                TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(
            "Content must shorter than 150 characters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnForbiddenWhenUserIsNotAuthenticated() throws Exception {
    mockMvc.perform(post(URL)
        .content(buildRequest(TESTIMONIAL_NAME, TESTIMONIAL_CONTENT, TESTIMONIAL_IMAGE))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)));
  }

  private void assertTestimonialHasBeenCreated(Long id) {
    Optional<TestimonialEntity> optionalTestimonialEntity = testimonialRepository.findById(id);
    assertTrue(optionalTestimonialEntity.isPresent());
    assertEquals(TESTIMONIAL_NAME, optionalTestimonialEntity.get().getName());
    assertEquals(TESTIMONIAL_CONTENT, optionalTestimonialEntity.get().getContent());
    assertEquals(TESTIMONIAL_IMAGE, optionalTestimonialEntity.get().getImageUrl());
    cleanTestimonialData(optionalTestimonialEntity.get());
  }

  private String buildRequest(String name, String content, String image)
      throws JsonProcessingException {
    return convert(new CreateTestimonialRequest(name, content, image));
  }
}
