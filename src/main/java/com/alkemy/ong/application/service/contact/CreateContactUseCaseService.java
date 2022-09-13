package com.alkemy.ong.application.service.contact;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.contact.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.util.mail.IMailSender;
import com.alkemy.ong.application.util.mail.template.ContactConfirmationEmailTemplate;
import com.alkemy.ong.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CreateContactUseCaseService implements ICreateContactUseCase {

  private final IContactRepository contactRepository;

  private final IOrganizationRepository organizationRepository;

  private final IMailSender mailSender;

  @Override
  public Contact create(Contact contact) {
    sendContactConfirmationEmail(contact);
    return contactRepository.create(contact);
  }

  private void sendContactConfirmationEmail(Contact contact) {
    try {
      mailSender.send(
          new ContactConfirmationEmailTemplate(contact, organizationRepository.getOrganization()));
    } catch (Exception e) {
      log.error("Something went wrong when sending email. Reason: " + e.getMessage());
    }
  }
}
