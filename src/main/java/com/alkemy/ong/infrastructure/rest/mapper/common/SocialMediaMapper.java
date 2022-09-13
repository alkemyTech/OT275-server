package com.alkemy.ong.infrastructure.rest.mapper.common;

import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.common.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.response.common.SocialMediaResponse;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaMapper {

  public SocialMedia toDomain(SocialMediaRequest socialMediaRequest) {
    if (socialMediaRequest == null) {
      return null;
    }
    SocialMedia socialMedia = new SocialMedia();
    socialMedia.setInstagramUrl(socialMediaRequest.getInstagramUrl());
    socialMedia.setFacebookUrl(socialMediaRequest.getFacebookUrl());
    socialMedia.setLinkedInUrl(socialMediaRequest.getLinkedInUrl());

    return socialMedia;
  }

  public SocialMediaResponse toResponse(SocialMedia socialMedia) {
    if (socialMedia == null) {
      return null;
    }
    SocialMediaResponse socialMediaResponse = new SocialMediaResponse();
    socialMediaResponse.setFacebookUrl(socialMedia.getFacebookUrl());
    socialMediaResponse.setInstagramUrl(socialMedia.getInstagramUrl());
    socialMediaResponse.setLinkedInUrl(socialMedia.getLinkedInUrl());

    return socialMediaResponse;
  }
}
