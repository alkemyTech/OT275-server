package com.alkemy.ong.application.util.mail.template;

import com.alkemy.ong.application.util.mail.IAddressContact;
import com.alkemy.ong.application.util.mail.IMail;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WelcomeEmailTemplate implements IMail {

  private static final String TEXT_HTML = "text/html";
  private static final String TEMPLATE_HTML = "template/welcome-email.html";
  private final IAddressContact addressContact;
  private final Organization organization;

  private static InputStream getFileFromResource() {
    return WelcomeEmailTemplate.class.getClassLoader().getResourceAsStream(TEMPLATE_HTML);
  }

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
    return TEXT_HTML;
  }

  private String setContent() throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(getFileFromResource()))) {
      return replacePlaceHolders(reader.readLine());
    }
  }

  public String replacePlaceHolders(String htmlContent) {
    String html = htmlContent.replace(PlaceHolder.LOGO.name(), organization.getImage())
        .replace(PlaceHolder.ORG_NAME.name(), organization.getName())
        .replace(PlaceHolder.WELCOME_TEXT.name(), organization.getWelcomeText())
        .replace(PlaceHolder.ORG_EMAIL.name(), organization.getEmail())
        .replace(PlaceHolder.ORG_ADDRESS.name(), organization.getAddress())
        .replace(PlaceHolder.ORG_PHONE.name(), organization.getPhone());

    SocialMedia socialMedia = organization.getSocialMedia();
    if (socialMedia != null) {
      html = html.replace(PlaceHolder.INSTAGRAM_URL.name(), socialMedia.getInstagramUrl())
          .replace(PlaceHolder.FACEBOOK_URL.name(), socialMedia.getFacebookUrl())
          .replace(PlaceHolder.LINKEDIN_URL.name(), socialMedia.getLinkedInUrl());
    }
    return html;
  }

  private enum PlaceHolder {

    LOGO,
    ORG_NAME,
    ORG_ADDRESS,
    WELCOME_TEXT,
    ORG_PHONE,
    ORG_EMAIL,
    LINKEDIN_URL,
    FACEBOOK_URL,
    INSTAGRAM_URL

  }
}
