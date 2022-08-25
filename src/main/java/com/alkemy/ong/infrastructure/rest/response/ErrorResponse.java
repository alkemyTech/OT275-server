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

  private String statusCode;
  private String message;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<String> moreInfo;

  public ErrorResponse(String statusCode, String message, String moreInfo) {
    this.statusCode = statusCode;
    this.message = message;
    this.moreInfo = List.of(moreInfo);
  }

}
