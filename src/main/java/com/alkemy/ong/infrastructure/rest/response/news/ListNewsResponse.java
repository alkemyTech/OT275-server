package com.alkemy.ong.infrastructure.rest.response.news;

import com.alkemy.ong.infrastructure.rest.response.common.PaginationResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListNewsResponse extends PaginationResponse {

  List<GetNewsResponse> news;

}
