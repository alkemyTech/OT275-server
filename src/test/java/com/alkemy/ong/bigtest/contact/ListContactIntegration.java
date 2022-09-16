package com.alkemy.ong.bigtest.contact;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ListContactIntegration extends BigTest {

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
    contactRepository.deleteAll();

    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts").value(empty()))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnContactListWhenThereAreContacts() throws Exception {
    contactRepository.deleteAll();
    saveContact(NAME, PHONE, EMAIL, MESSAGE);

    mockMvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts", hasSize(1)))
        .andExpect(jsonPath("$.contacts[0].id", equalTo(1)))
        .andExpect(jsonPath("$.contacts[0].name", equalTo(NAME)))
        .andExpect(jsonPath("$.contacts[0].phone", equalTo(PHONE)))
        .andExpect(jsonPath("$.contacts[0].email", equalTo(EMAIL)))
        .andExpect(status().isOk());
  }

  private void saveContact(String name, String phone, String email, String message) {
    ContactEntity contactEntity = new ContactEntity();
    contactEntity.setName(name);
    contactEntity.setPhone(phone);
    contactEntity.setEmail(email);
    contactEntity.setMessage(message);
    contactRepository.save(contactEntity);
  }
}
