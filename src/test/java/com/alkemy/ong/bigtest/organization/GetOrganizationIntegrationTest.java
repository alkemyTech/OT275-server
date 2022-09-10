package com.alkemy.ong.bigtest.organization;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;

public class GetOrganizationIntegrationTest extends BigTest {

  private static final String URL = "/organization/public";


  @Test
  public void shouldGetOrganizationWithoutCredentials() throws Exception {
    mockMvc.perform(get(URL))
        .andExpect(jsonPath("$.name", equalTo("Somos Mas")))
        .andExpect(jsonPath("$.image", equalTo("https://s3.com/logo.jpg/")))
        .andExpect(jsonPath("$.phone", equalTo("+540303456")))
        .andExpect(jsonPath("$.address", equalTo("Elm Street 3")))
        .andExpect(jsonPath("$.slides", hasSize(0)))
        .andExpect(jsonPath("$.socialMedia", notNullValue()))
        .andExpect(jsonPath("$.socialMedia.facebookUrl",
            equalTo("https://www.facebook.com/Somos_Mas/")))
        .andExpect(jsonPath("$.socialMedia.linkedInUrl",
            equalTo("https://www.linkedin.com/in/Somos-Mas/")))
        .andExpect(jsonPath("$.socialMedia.instagramUrl",
            equalTo("https://www.instagram.com/SomosMas/")))
        .andExpect(status().isOk());
  }

}