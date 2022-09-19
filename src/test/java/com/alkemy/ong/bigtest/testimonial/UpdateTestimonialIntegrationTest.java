package com.alkemy.ong.bigtest.testimonial;

import com.alkemy.ong.bigtest.BigTest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import com.alkemy.ong.infrastructure.rest.request.testimonial.UpdateTestimonialRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateTestimonialIntegrationTest extends BigTest {

  private static final String URL = "/testimonials/{id}";
  private static final String NEW_TESTIMONIAL_NAME = "New testimonial name";
  private static final String NEW_TESTIMONIAL_CONTENT = "New testimonial content";
  private static final String NEW_TESTIMONIAL_IMAGE = "new-testimonial-image.jpg";

  @Test
  public void shouldUpdateTestimonialWhenUserHasAdminRole() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();

    mockMvc.perform(put(URL, String.valueOf(randomTestimonialId))
            .content(buildRequest(NEW_TESTIMONIAL_NAME, NEW_TESTIMONIAL_CONTENT, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo(NEW_TESTIMONIAL_NAME)))
        .andExpect(jsonPath("$.content", equalTo(NEW_TESTIMONIAL_CONTENT)))
        .andExpect(jsonPath("$.imageUrl", equalTo(NEW_TESTIMONIAL_IMAGE)))
        .andExpect(status().isOk());

    assertTestimonialHasBeenUpdated(randomTestimonialId);
    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldUpdateTestimonialWhenUserHasStandardUserRole() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();

    mockMvc.perform(put(URL, String.valueOf(randomTestimonialId))
            .content(buildRequest(NEW_TESTIMONIAL_NAME, NEW_TESTIMONIAL_CONTENT, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo(NEW_TESTIMONIAL_NAME)))
        .andExpect(jsonPath("$.content", equalTo(NEW_TESTIMONIAL_CONTENT)))
        .andExpect(jsonPath("$.imageUrl", equalTo(NEW_TESTIMONIAL_IMAGE)))
        .andExpect(status().isOk());

    assertTestimonialHasBeenUpdated(randomTestimonialId);
    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnForbiddenWhenUserIsNotAuthenticated() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();

    mockMvc.perform(put(URL, String.valueOf(randomTestimonialId))
            .content(buildRequest(NEW_TESTIMONIAL_NAME, NEW_TESTIMONIAL_CONTENT, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.statusCode", Matchers.equalTo(403)))
        .andExpect(jsonPath("$.message", Matchers.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());

    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnNotFoundWhenTestimonialDoesNotExist() throws Exception {
    String nonExistentTestimonialId = "999999999";

    mockMvc.perform(put(URL, nonExistentTestimonialId)
            .content(buildRequest(NEW_TESTIMONIAL_NAME, NEW_TESTIMONIAL_CONTENT, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Testimonial not found.")))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnBadRequestWhenNameIsEmpty() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();

    mockMvc.perform(put(URL, String.valueOf(randomTestimonialId))
            .content(buildRequest("", NEW_TESTIMONIAL_CONTENT, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo", hasItems("Name cannot be empty.", "Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());

    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnBadRequestWhenNameSizeIsGreaterThanAllowed() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();
    String nameGreaterThanAllowed = RandomStringUtils.random(51, "x");

    mockMvc.perform(put("/testimonials/{id}", String.valueOf(randomTestimonialId))
            .content(buildRequest(nameGreaterThanAllowed, NEW_TESTIMONIAL_CONTENT, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must be 50 characters or less.")))
        .andExpect(status().isBadRequest());

    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnBadRequestWhenContentSizeGreaterThanAllowed() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();
    String contentSizeGreaterThanAllowed = RandomStringUtils.random(151, "x");

    mockMvc.perform(put(URL, String.valueOf(randomTestimonialId))
            .content(buildRequest(NEW_TESTIMONIAL_NAME, contentSizeGreaterThanAllowed, NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo",
            hasItem("Content must be 150 characters or less.")))
        .andExpect(status().isBadRequest());

    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnBadRequestWhenNameContainsNumbers() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();
    Long randomTestimonialId = randomTestimonial.getTestimonialId();

    mockMvc.perform(put(URL, String.valueOf(randomTestimonialId))
            .content(
                buildRequest("Upd4t3d T3st1m0n14l w17h numb3rs", NEW_TESTIMONIAL_CONTENT,
                    NEW_TESTIMONIAL_IMAGE))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());

    cleanTestimonialData(randomTestimonial);
  }

  private void assertTestimonialHasBeenUpdated(Long testimonialId) {
    Optional<TestimonialEntity> optionalTestimonialEntity = testimonialRepository.findById(
        testimonialId);
    assertTrue(!optionalTestimonialEntity.isEmpty());
    assertEquals(NEW_TESTIMONIAL_NAME, optionalTestimonialEntity.get().getName());
    assertEquals(NEW_TESTIMONIAL_CONTENT, optionalTestimonialEntity.get().getContent());
    assertEquals(NEW_TESTIMONIAL_IMAGE, optionalTestimonialEntity.get().getImageUrl());
    cleanTestimonialData(optionalTestimonialEntity.get());
  }

  private String buildRequest(String name, String content, String image)
      throws JsonProcessingException {
    return convert(new UpdateTestimonialRequest(name, content, image));
  }

}