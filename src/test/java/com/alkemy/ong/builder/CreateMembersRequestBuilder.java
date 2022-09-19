package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;

public class CreateMembersRequestBuilder {

  public static CreateMemberRequest buildRequest(String name, String imageUrl,
      String description, String facebookUrl, String linkedinUrl
      , String instagramUrl) {
    CreateMemberRequest memberRequest = new CreateMemberRequest();
    memberRequest.setName(name);
    memberRequest.setImage(imageUrl);
    memberRequest.setDescription(description);
    memberRequest.setSocialMedia(
        SocialMediaRequestBuilder.buildRequest(facebookUrl, linkedinUrl, instagramUrl));
    return memberRequest;
  }
}
