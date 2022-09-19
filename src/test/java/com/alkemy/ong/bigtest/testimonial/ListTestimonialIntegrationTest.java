package com.alkemy.ong.bigtest.testimonial;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ListTestimonialIntegrationTest extends BigTest {

  public static final String URL = "/testimonials";
  private static final String TESTIMONIAL_NAME = "Testimonial name";
  private static final String TESTIMONIAL_CONTENT = "Testimonial content";
  private static final String TESTIMONIAL_IMAGE = "testimonial-image.jpg";

  @Test
  public void shouldReturnListOfTestimonialsWhenUserHasAdminRole() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();

    mockMvc.perform(get(URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.testimonials[*].id", notNullValue()))
        .andExpect(jsonPath("$.testimonials[*].name")
            .value(hasItem(TESTIMONIAL_NAME)))
        .andExpect(jsonPath("$.testimonials[*].content")
            .value(hasItem(TESTIMONIAL_CONTENT)))
        .andExpect(jsonPath("$.testimonials[*].imageUrl")
            .value(hasItem(TESTIMONIAL_IMAGE)))
        .andExpect(jsonPath("$.testimonials", hasSize(1)))
        .andExpect(status().isOk());

    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnListOfTestimonialsWhenUserHasStandardUserRole() throws Exception {
    TestimonialEntity randomTestimonial = getRandomTestimonial();

    mockMvc.perform(get(URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.testimonials[*].id", notNullValue()))
        .andExpect(jsonPath("$.testimonials[*].name")
            .value(hasItem(TESTIMONIAL_NAME)))
        .andExpect(jsonPath("$.testimonials[*].imageUrl")
            .value(hasItem(TESTIMONIAL_IMAGE)))
        .andExpect(jsonPath("$.testimonials[*].content")
            .value(hasItem(TESTIMONIAL_CONTENT)))
        .andExpect(jsonPath("$.testimonials", hasSize(1)))
        .andExpect(status().isOk());

    cleanTestimonialData(randomTestimonial);
  }

  @Test
  public void shouldReturnForbiddenWhenUserIsNotAuthenticated() throws Exception {
    mockMvc.perform(get(URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.statusCode", Matchers.equalTo(403)))
        .andExpect(jsonPath("$.message", Matchers.equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnAnEmptyListOfTestimonialsWhenTestimonialsIsEmpty() throws Exception {
    mockMvc.perform(get(URL)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.testimonials").value(empty()))
        .andExpect(status().isOk());
  }

}