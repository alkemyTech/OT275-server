package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {

  public Page<Member> toPageDomain(List<MemberEntity> entities,
      int pages,
      int size,
      long totalElements) {
    return new PageImpl<>(
        toDomain(entities),
        PageRequest.of(pages, size), totalElements
    );
  }

  private List<Member> toDomain(List<MemberEntity> entities) {
    if (entities == null || entities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Member> members = new ArrayList<>(entities.size());
    for (MemberEntity member : entities) {
      members.add(toDomain(member));
    }
    return members;
  }

  public Member toDomain(MemberEntity entity) {
    if (entity == null) {
      return null;
    }
    Member member = new Member();
    member.setMemberId(entity.getMemberId());
    member.setName(entity.getName());
    member.setSocialMedia(buildSocialMedia(entity));
    member.setImageUrl(entity.getImageUrl());
    member.setDescription(entity.getDescription());
    return member;
  }

  private SocialMedia buildSocialMedia(MemberEntity entity) {
    SocialMedia socialMedia = new SocialMedia();
    socialMedia.setFacebookUrl(entity.getFacebookUrl());
    socialMedia.setInstagramUrl(entity.getInstagramUrl());
    socialMedia.setLinkedInUrl(entity.getLinkedInUrl());
    return socialMedia;
  }

  public MemberEntity toEntity(Member member) {
    if (member == null) {
      return null;
    }
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setMemberId(member.getMemberId());
    memberEntity.setName(member.getName());
    memberEntity.setImageUrl(member.getImageUrl());
    memberEntity.setDescription(member.getDescription());
    SocialMedia socialMedia = member.getSocialMedia();
    if (socialMedia != null) {
      memberEntity.setFacebookUrl(socialMedia.getFacebookUrl());
      memberEntity.setInstagramUrl(socialMedia.getInstagramUrl());
      memberEntity.setLinkedInUrl(socialMedia.getLinkedInUrl());
    }
    return memberEntity;
  }

}
