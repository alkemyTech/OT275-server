package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.common.SocialMediaRequest;

public class SocialMediaRequestBuilder {

  public static SocialMediaRequest defaultSocialMediaRequest() {
    return buildRequest("https://www.facebook.com/somos_mas/",
        "https://www.linkedin.com/in/somos_mas/",
        "https://www.instagram.com/SOMOSMAS/");
  }

  public static SocialMediaRequest buildRequest(String facebookUrl,String linkedinUrl
      ,String instagramUrl) {
    SocialMediaRequest socialMediaRequest = new SocialMediaRequest();
    socialMediaRequest.setFacebookUrl(facebookUrl);
    socialMediaRequest.setLinkedInUrl(linkedinUrl);
    socialMediaRequest.setInstagramUrl(instagramUrl);
    return socialMediaRequest;
  }

}
