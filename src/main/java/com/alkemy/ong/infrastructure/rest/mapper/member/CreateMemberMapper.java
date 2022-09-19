package com.alkemy.ong.infrastructure.rest.mapper.member;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateMemberMapper extends GetMemberMapper {

  public CreateMemberMapper(SocialMediaMapper socialMediaMapper) {
    super(socialMediaMapper);
  }

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

}
