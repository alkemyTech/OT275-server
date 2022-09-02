package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IContact;
import com.alkemy.ong.application.util.IMail;
import com.alkemy.ong.application.util.OrganizationInfo;
import com.alkemy.ong.domain.Organization;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WelcomeEmailTemplate implements IMail {

  private final IContact contact;
  private final Organization organization;
  private static final String FILE_WELCOME_EMAIL_HTML =
      "src/main/resources/template/welcome-email.html";

  @Override
  public String getSubject() {
    return organization.getName();
  }

  @Override
  public String getTo() {
    return contact.getEmail();
  }

  @Override
  public String getContent() throws IOException {
    return this.setContent(Files.readString(Path.of(FILE_WELCOME_EMAIL_HTML)));
  }

  @Override
  public String getContentType() {
    return "text/html";
  }

  public String setContent(String readString) {
    return readString.replace(OrganizationInfo.IMAGE.name(), organization.getImage())
        .replace(OrganizationInfo.ORG_NAME.name(), organization.getName())
        .replace(OrganizationInfo.ORG_EMAIL.name(), organization.getEmail())
        .replace(OrganizationInfo.WELCOME_TEXT.name(), organization.getWelcomeText())
        .replace(OrganizationInfo.ORG_ADDRESS.name(), organization.getAddress())
        .replace(OrganizationInfo.ORG_PHONE.name(), organization.getPhone())
        .replace(OrganizationInfo.INSTAGRAM_URL.name(),
            organization.getSocialNetwork().getInstagramUrl())
        .replace(OrganizationInfo.FACEBOOK_URL.name(),
            organization.getSocialNetwork().getFacebookUrl())
        .replace(OrganizationInfo.LINKEDIN_URL.name(),
            organization.getSocialNetwork().getLinkedinUrl());
  }
}
