package com.alkemy.ong.infrastructure.rest.mapper.member;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;
import com.alkemy.ong.infrastructure.rest.response.member.UpdateMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateMemberMapper extends CreateMemberMapper {

  public UpdateMemberMapper(
      SocialMediaMapper socialMediaMapper) {
    super(socialMediaMapper);
  }

  public Member toDomain(Long id, CreateMemberRequest request) {
    Member member = super.toDomain(request);
    member.setMemberId(id);
    return member;
  }

  public UpdateMemberResponse toResponse(Member member){
    return (UpdateMemberResponse) super.toResponse(member);
  }
}
