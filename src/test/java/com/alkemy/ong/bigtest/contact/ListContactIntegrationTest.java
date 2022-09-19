package com.alkemy.ong.bigtest.contact;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ListContactIntegrationTest extends BigTest {

  private static final String URL = "/contacts";
  private static final String NAME = "My Contact";
  private static final String PHONE = "12345678";
  private static final String EMAIL = "email@gmail.com";
  private static final String MESSAGE = "My contact message";

  @Test
  public void shouldReturnForbiddenWhenUserHasStandardRole() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(ACCESS_DENIED_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem(ACCESS_DENIED_MORE_INFO)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnEmptyListWhenThereAreNoContact() throws Exception {
    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts").value(empty()))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnContactListWhenThereAreContacts() throws Exception {
    createContact(NAME, PHONE, EMAIL, MESSAGE);

    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts", hasSize(1)))
        .andExpect(jsonPath("$.contacts[0].id", notNullValue()))
        .andExpect(jsonPath("$.contacts[0].name", equalTo(NAME)))
        .andExpect(jsonPath("$.contacts[0].phone", equalTo(PHONE)))
        .andExpect(jsonPath("$.contacts[0].email", equalTo(EMAIL)))
        .andExpect(status().isOk());
  }

}
