package com.alkemy.ong.infrastructure.rest.mapper.member;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.response.member.GetMemberResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemberMapper {

  public final SocialMediaMapper socialMediaMapper;

  public List<GetMemberResponse> toResponse(List<Member> members) {
    if (members == null || members.isEmpty()) {
      return Collections.emptyList();
    }
    List<GetMemberResponse> memberResponses = new ArrayList<>(members.size());
    for (Member member : members) {
      memberResponses.add(toResponse(member));
    }
    return memberResponses;
  }

  public GetMemberResponse toResponse(Member member) {
    if (member == null) {
      return null;
    }
    GetMemberResponse response = new GetMemberResponse();
    response.setMemberId(member.getMemberId());
    response.setName(member.getName());
    response.setSocialMedia(socialMediaMapper.toResponse(member.getSocialMedia()));
    response.setImageUrl(member.getImageUrl());
    response.setDescription(member.getDescription());
    return response;
  }
}
