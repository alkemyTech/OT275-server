package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.SocialMediaRequest;

public class SocialMediaRequestBuilder {

  public static SocialMediaRequest defaultSocialMediaRequest() {
    SocialMediaRequest socialMediaRequest = new SocialMediaRequest();
    socialMediaRequest.setFacebookUrl("https://www.facebook.com/somos_mas/");
    socialMediaRequest.setLinkedInUrl("https://www.linkedin.com/in/somos_mas/");
    socialMediaRequest.setInstagramUrl("https://www.instagram.com/SOMOSMAS/");
    return socialMediaRequest;
  }

}
