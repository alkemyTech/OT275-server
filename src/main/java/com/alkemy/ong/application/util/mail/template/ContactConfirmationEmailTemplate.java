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
import org.springframework.http.MediaType;

@RequiredArgsConstructor
public class ContactConfirmationEmailTemplate implements IMail {

  private static final String TEMPLATE_HTML = "template/contact-confirmation-email.html";
  private final IAddressContact addressContact;
  private final Organization organization;

  private static InputStream getFileFromResource() {
    return ContactConfirmationEmailTemplate.class.getClassLoader()
        .getResourceAsStream(TEMPLATE_HTML);
  }

  @Override
  public String getSubject() {
    return organization.getName();
  }

  @Override
  public String getTo() {
    return addressContact.getEmail();
  }

  @Override
  public String getContent() throws IOException {
    return setContent();
  }

  private String setContent() throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(getFileFromResource()))) {
      return replacePlaceHolders(reader.readLine());
    }
  }

  private String replacePlaceHolders(String htmlContent) {
    String html = htmlContent
        .replace(ContactConfirmationEmailTemplate.PlaceHolder.LOGO.name(),
            organization.getImage())
        .replace(ContactConfirmationEmailTemplate.PlaceHolder.ORG_NAME.name(),
            organization.getName())
        .replace(ContactConfirmationEmailTemplate.PlaceHolder.ORG_EMAIL.name(),
            organization.getEmail())
        .replace(ContactConfirmationEmailTemplate.PlaceHolder.ORG_ADDRESS.name(),
            organization.getAddress())
        .replace(ContactConfirmationEmailTemplate.PlaceHolder.ORG_PHONE.name(),
            organization.getPhone());

    SocialMedia socialMedia = organization.getSocialMedia();
    if (socialMedia != null) {
      html = html
          .replace(ContactConfirmationEmailTemplate.PlaceHolder.INSTAGRAM_URL.name(),
              socialMedia.getInstagramUrl())
          .replace(ContactConfirmationEmailTemplate.PlaceHolder.FACEBOOK_URL.name(),
              socialMedia.getFacebookUrl())
          .replace(ContactConfirmationEmailTemplate.PlaceHolder.LINKEDIN_URL.name(),
              socialMedia.getLinkedInUrl());
    }

    return html;
  }

  @Override
  public String getContentType() {
    return MediaType.TEXT_HTML_VALUE;
  }

  private enum PlaceHolder {

    LOGO,
    ORG_NAME,
    ORG_ADDRESS,
    ACKNOWLEDGMENT_TEXT,
    ORG_PHONE,
    ORG_EMAIL,
    LINKEDIN_URL,
    FACEBOOK_URL,
    INSTAGRAM_URL

  }

}
