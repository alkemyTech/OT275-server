package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.service.contact.ListContactUseCaseService;
import com.alkemy.ong.domain.Contact;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListContactUseCaseServiceTest {

  private ListContactUseCaseService listContactUseCaseService;

  @Mock
  private IContactRepository contactRepository;

  @BeforeEach
  void setUp() {
    listContactUseCaseService = new ListContactUseCaseService(contactRepository);
  }

  @Test
  void shouldReturnListOfContacts() {
    List<Contact> contacts = new ArrayList<>();
    given(contactRepository.findAll()).willReturn(contacts);

    List<Contact> result = listContactUseCaseService.findAll();

    assertEquals(contacts, result);
    verify(contactRepository, times(1)).findAll();

  }
}
