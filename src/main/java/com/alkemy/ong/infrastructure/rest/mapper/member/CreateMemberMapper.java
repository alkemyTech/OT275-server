package com.alkemy.ong.infrastructure.rest.mapper.member;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;
import com.alkemy.ong.infrastructure.rest.response.common.SocialMediaResponse;
import com.alkemy.ong.infrastructure.rest.response.member.GetMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateMemberMapper {

  public Member toDomain(CreateMemberRequest createMemberRequest) {
    if (createMemberRequest == null) {
      return null;
    }
    Member member = new Member();
    member.setName(createMemberRequest.getName());
    member.setImageUrl(createMemberRequest.getImage());
    member.setDescription(createMemberRequest.getDescription());
    member.setSocialMedia(socialMediaBuilder(createMemberRequest));
    return member;
  }

  private SocialMedia socialMediaBuilder(CreateMemberRequest createMemberRequest) {
    if (createMemberRequest == null) {
      return null;
    }
    SocialMedia socialMedia = new SocialMedia();
    socialMedia.setFacebookUrl(createMemberRequest.getFacebookUrl());
    socialMedia.setInstagramUrl(createMemberRequest.getInstagramUrl());
    socialMedia.setLinkedInUrl(createMemberRequest.getLinkedInUrl());
    return socialMedia;
  }

  public GetMemberResponse toResponse(Member member) {
    if (member == null) {
      return null;
    }
    GetMemberResponse memberResponse = new GetMemberResponse();
    memberResponse.setMemberId(member.getMemberId());
    memberResponse.setName(member.getName());
    memberResponse.setImageUrl(member.getImageUrl());
    memberResponse.setDescription(member.getDescription());
    memberResponse.setSocialMedia(buildSocialMedia(member));
    return memberResponse;
  }

  private SocialMediaResponse buildSocialMedia(Member member) {
    if (member == null) {
      return null;
    }
    SocialMediaResponse socialMediaResponse = new SocialMediaResponse();
    socialMediaResponse.setFacebookUrl(member.getSocialMedia().getFacebookUrl());
    socialMediaResponse.setInstagramUrl(member.getSocialMedia().getInstagramUrl());
    socialMediaResponse.setLinkedInUrl(member.getSocialMedia().getLinkedInUrl());
    return socialMediaResponse;
  }

}
