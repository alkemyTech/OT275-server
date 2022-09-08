package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.util.mail.IMailSender;
import com.alkemy.ong.application.util.mail.template.WelcomeEmailTemplate;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CreateUserUseCaseService implements ICreateUserUseCase {

  private final IUserRepository userRepository;
  private final IRoleRepository roleRepository;
  private final IOrganizationRepository organizationRepository;
  private final IMailSender mailSender;

  @Override
  public User add(User user) {
    if (userRepository.findBy(user.getEmail()).isPresent()) {
      throw new UserAlreadyExistsException(ErrorMessage.USER_ALREADY_EXISTS.getMessage());
    }
    user.setRole(roleRepository.findRoleUser());
    sendWelcomeMail(user);
    return userRepository.add(user);
  }

  private void sendWelcomeMail(User user) {
    try {
      WelcomeEmailTemplate emailTemplate = new WelcomeEmailTemplate(user::getEmail,
          organizationRepository.getOrganization());
      mailSender.send(emailTemplate);
    } catch (Exception ex) {
      log.error("Something went wrong when sending email. Reason: " + ex.getMessage());
    }
  }

}
