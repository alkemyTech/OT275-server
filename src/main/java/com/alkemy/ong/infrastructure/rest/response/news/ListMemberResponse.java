package com.alkemy.ong.infrastructure.rest.response.news;

import com.alkemy.ong.infrastructure.rest.response.common.PaginationResponse;
import com.alkemy.ong.infrastructure.rest.response.member.GetMemberResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListMemberResponse extends PaginationResponse {

  private List<GetMemberResponse> members;

}
