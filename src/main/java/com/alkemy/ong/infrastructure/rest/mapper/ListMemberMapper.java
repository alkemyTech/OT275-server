package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.response.ListMemberResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListMemberMapper {

  private final GetMemberMapper getMemberMapper;

  public ListMemberResponse toResponse(Page<Member> members) {
    ListMemberResponse listMemberResponse = toResponse(members.getContent());
    listMemberResponse.setPage(members.getNumber());
    listMemberResponse.setSize(members.getSize());
    listMemberResponse.setTotalPages(members.getTotalPages());
    return listMemberResponse;
  }

  private ListMemberResponse toResponse(List<Member> members) {
    if (members == null) {
      return new ListMemberResponse(Collections.emptyList());
    }
    return new ListMemberResponse(getMemberMapper.toResponse(members));
  }
}
