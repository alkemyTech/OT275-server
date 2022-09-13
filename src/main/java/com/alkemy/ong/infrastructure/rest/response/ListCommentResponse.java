package com.alkemy.ong.infrastructure.rest.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ListCommentResponse {

  private List<GetCommentResponse> comments;

}
