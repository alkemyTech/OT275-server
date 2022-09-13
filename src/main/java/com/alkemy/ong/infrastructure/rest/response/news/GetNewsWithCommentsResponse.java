package com.alkemy.ong.infrastructure.rest.response.news;

import com.alkemy.ong.infrastructure.rest.response.comment.GetCommentResponse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNewsWithCommentsResponse {

  private String name;

  private List<GetCommentResponse> comments;

}
