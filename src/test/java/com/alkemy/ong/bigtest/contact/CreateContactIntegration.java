package com.alkemy.ong.bigtest.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import com.alkemy.ong.infrastructure.rest.request.contact.CreateContactRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateContactIntegration extends BigTest {

  private static final String URL = "/contacts";
  private static final String NAME = "My Contact";
  private static final String PHONE = "12345678";
  private static final String EMAIL = "email@gmail.com";
  private static final String MESSAGE = "My contact message";

  @Test
  public void shouldCreateContactWhenUserHasAdminRole() throws Exception {
    String contactResponse = mockMvc.perform(post(URL)
            .content(buildRequest(NAME, PHONE, EMAIL, MESSAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.phone", equalTo(PHONE)))
        .andExpect(jsonPath("$.email", equalTo(EMAIL)))
        .andExpect(jsonPath("$.message", equalTo(MESSAGE)))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    Integer contactId = JsonPath.read(contactResponse, "$.id");
    assertActivityHasBeenCreated(contactId.longValue());
  }

  @Test
  public void shouldCreateContactWhenUserHasStandardRole() throws Exception {
    String contactResponse = mockMvc.perform(post(URL)
            .content(buildRequest(NAME, PHONE, EMAIL, MESSAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.phone", equalTo(PHONE)))
        .andExpect(jsonPath("$.email", equalTo(EMAIL)))
        .andExpect(jsonPath("$.message", equalTo(MESSAGE)))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    Integer contactId = JsonPath.read(contactResponse, "$.id");
    assertActivityHasBeenCreated(contactId.longValue());
  }

  @Test
  public void shouldReturnBadRequestWhenNameHasNumbers() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest("1", PHONE, EMAIL, MESSAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Name must contain only spaces and letters.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenEmailIsIncorrect() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, PHONE, "123", MESSAGE))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Email should be valid.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnBadRequestWhenMessageHasMoreThanAllowedCharacters() throws Exception {
    mockMvc.perform(post(URL)
            .content(buildRequest(NAME, PHONE, EMAIL, "x".repeat(151)))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(400)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(INVALID_INPUT_DATA_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(
            jsonPath("$.moreInfo", hasItem("Message length must be"
                + " shorter than 150 characters")))
        .andExpect(status().isBadRequest());
  }


  private void assertActivityHasBeenCreated(Long id) {
    Optional<ContactEntity> contactEntity = contactRepository.findById(id);
    assertTrue(contactEntity.isPresent());
    assertThat(contactEntity.get().getDeletedAt()).isNull();
    assertEquals(NAME, contactEntity.get().getName());
    assertEquals(PHONE, contactEntity.get().getPhone());
    assertEquals(EMAIL, contactEntity.get().getEmail());
    assertEquals(MESSAGE, contactEntity.get().getMessage());
  }

  private String buildRequest(String name, String phone, String email, String message)
      throws JsonProcessingException {
    CreateContactRequest createContactRequest = new CreateContactRequest(name, phone, email,
        message);
    return convert(createContactRequest);
  }
}
