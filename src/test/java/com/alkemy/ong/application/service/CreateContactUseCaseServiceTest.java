package com.alkemy.ong.application.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.contact.CreateContactUseCaseService;
import com.alkemy.ong.application.util.mail.IMailSender;
import com.alkemy.ong.domain.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateContactUseCaseServiceTest {

  private CreateContactUseCaseService createContactUseCaseService;
  @Mock
  private IContactRepository contactRepository;

  @Mock
  private IMailSender mailSender;

  @Mock
  private IOrganizationRepository organizationRepository;

  @BeforeEach
  void setUp() {
    createContactUseCaseService = new CreateContactUseCaseService(contactRepository,
        organizationRepository,
        mailSender);
  }

  @Test
  void shouldCreateContact() {
    Contact contact = new Contact();

    given(contactRepository.create(contact)).willReturn(contact);
    createContactUseCaseService.create(contact);

    verify(contactRepository, times(1)).create(contact);
  }
}