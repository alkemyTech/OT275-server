package com.alkemy.ong.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
  private int statusCode;
  private String message;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<String> moreInfo;

}
