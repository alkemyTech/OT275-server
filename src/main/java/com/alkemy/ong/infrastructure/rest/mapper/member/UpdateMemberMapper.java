package com.alkemy.ong.infrastructure.rest.mapper.member;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.request.member.UpdateMemberRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateMemberMapper extends CreateMemberMapper {

  public UpdateMemberMapper(
      SocialMediaMapper socialMediaMapper) {
    super(socialMediaMapper);
  }

  public Member toDomain(Long id, UpdateMemberRequest request) {
    if (request == null) {
      return null;
    }
    Member member = new Member();
    member.setMemberId(id);
    member.setName(request.getName());
    member.setImageUrl(request.getImage());
    member.setDescription(request.getDescription());
    member.setSocialMedia(socialMediaMapper.toDomain(request.getSocialMedia()));
    return member;
  }
}
