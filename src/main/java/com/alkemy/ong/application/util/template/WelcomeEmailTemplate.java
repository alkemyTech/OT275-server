package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IAddressContact;
import com.alkemy.ong.application.util.IMail;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WelcomeEmailTemplate implements IMail {

  private final IAddressContact addressContact;
  private final Organization organization;

  @Override
  public String getSubject() {
    return organization.getWelcomeText();
  }

  @Override
  public String getTo() {
    return addressContact.getEmail();
  }

  @Override
  public String getContent() throws IOException {
    return setContent();
  }

  @Override
  public String getContentType() {
    return "text/html";
  }

  private String setContent() throws IOException {
    InputStream file = WelcomeEmailTemplate.class.getClassLoader()
        .getResourceAsStream("template/welcome-email-template.html");
    InputStreamReader streamReader = null;
    if (file != null) {
      streamReader = new InputStreamReader(file);
    }
    BufferedReader reader = new BufferedReader(streamReader);
    return replacePlaceHolders(reader.readLine());
  }

  public String replacePlaceHolders(String readFile) {
    SocialMedia socialMedia = organization.getSocialMedia();
    String html = "";
    if (socialMedia != null && !readFile.isEmpty()) {
      html += readFile.replace(PlaceHolder.LOGO.name(), organization.getImage())
          .replace(PlaceHolder.ORG_NAME.name(), organization.getName())
          .replace(PlaceHolder.WELCOME_TEXT.name(), organization.getWelcomeText())
          .replace(PlaceHolder.ORG_EMAIL.name(), organization.getEmail())
          .replace(PlaceHolder.ORG_ADDRESS.name(), organization.getAddress())
          .replace(PlaceHolder.ORG_PHONE.name(), organization.getPhone())
          .replace(PlaceHolder.INSTAGRAM_URL.name(),
              organization.getSocialMedia().getInstagramUrl())
          .replace(PlaceHolder.FACEBOOK_URL.name(),
              organization.getSocialMedia().getFacebookUrl())
          .replace(PlaceHolder.LINKEDIN_URL.name(),
              organization.getSocialMedia().getLinkedInUrl());
    }
    return html;
  }

  private enum PlaceHolder {

    LOGO,
    ORG_NAME,
    ORG_ADDRESS,
    WELCOME_TEXT,
    ORG_PHONE, ORG_EMAIL,
    LINKEDIN_URL,
    FACEBOOK_URL,
    INSTAGRAM_URL

  }
}
