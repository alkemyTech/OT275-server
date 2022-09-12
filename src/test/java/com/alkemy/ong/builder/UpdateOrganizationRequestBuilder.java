package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;

public class UpdateOrganizationRequestBuilder {

  public static UpdateOrganizationRequest defaultRequest() {
    UpdateOrganizationRequest updateOrganizationRequest = new UpdateOrganizationRequest();
    updateOrganizationRequest.setName("somos mas");
    updateOrganizationRequest.setImageUrl("https://s3.com/updatedlogo.jpg/");
    updateOrganizationRequest.setAddress("Elm Street 30");
    updateOrganizationRequest.setPhone("+540303111");
    updateOrganizationRequest.setEmail("somos.mas@ong.com.ar");
    updateOrganizationRequest.setSocialMedia(SocialMediaRequestBuilder.defaultSocialMediaRequest());
    updateOrganizationRequest.setAboutUsText("About ...");
    updateOrganizationRequest.setWelcomeText("Welcome to Somos mas");
    return updateOrganizationRequest;
  }

  public static UpdateOrganizationRequest buildRequest(String name, String email,
      String welcomeText) {
    UpdateOrganizationRequest updateOrganizationRequest = defaultRequest();
    updateOrganizationRequest.setName(name);
    updateOrganizationRequest.setEmail(email);
    updateOrganizationRequest.setWelcomeText(welcomeText);
    return updateOrganizationRequest;
  }
}
