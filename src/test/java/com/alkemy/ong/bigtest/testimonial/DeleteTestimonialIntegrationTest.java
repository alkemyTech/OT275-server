package com.alkemy.ong.bigtest.testimonial;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DeleteTestimonialIntegrationTest extends BigTest {

  private static final String URL = "/testimonials/{id}";

  @Test
  public void shouldDeleteTestimonialWhenUserHasAdminRole() throws Exception {
    Long id = saveTestimonial().getTestimonialId();

    mockMvc.perform(delete(URL, String.valueOf(id))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isNoContent());

    assertTestimonialHasBeenDeleted(id);
  }


  @Test
  public void shouldDeleteTestimonialWhenUserHasStandardUserRole() throws Exception {
    Long id = saveTestimonial().getTestimonialId();

    mockMvc.perform(delete(URL, String.valueOf(id))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(status().isNoContent());

    assertTestimonialHasBeenDeleted(id);
  }

  @Test
  public void shouldReturnForbiddenWhenUserIsNotAuthenticated() throws Exception {
    Long id = saveTestimonial().getTestimonialId();

    mockMvc.perform(delete(URL, String.valueOf(id))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnNotFoundWhenTestimonialDoesNotExist() throws Exception {
    mockMvc.perform(delete(URL, "1")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo(OBJECT_NOT_FOUND_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Testimonial not found.")))
        .andExpect(status().isNotFound());
  }

  private void assertTestimonialHasBeenDeleted(Long id) {
    Optional<TestimonialEntity> optionalTestimonialEntity = testimonialRepository.findById(id);
    assertFalse(optionalTestimonialEntity.isEmpty());
    assertTrue(optionalTestimonialEntity.get().isSoftDeleted());
  }
}