package com.alkemy.ong.infrastructure.rest.mapper.member;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;
import com.alkemy.ong.infrastructure.rest.response.member.GetMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateMemberMapper {

  private final SocialMediaMapper socialMediaMapper;

  public Member toDomain(CreateMemberRequest createMemberRequest) {
    if (createMemberRequest == null) {
      return null;
    }
    Member member = new Member();
    member.setName(createMemberRequest.getName());
    member.setImageUrl(createMemberRequest.getImage());
    member.setDescription(createMemberRequest.getDescription());
    member.setSocialMedia(socialMediaMapper.toDomain(createMemberRequest.getSocialMedia()));
    return member;
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
    memberResponse.setSocialMedia(socialMediaMapper.toResponse(member.getSocialMedia()));
    return memberResponse;
  }

}
