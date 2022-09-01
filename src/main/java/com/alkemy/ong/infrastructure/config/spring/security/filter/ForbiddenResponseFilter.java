package com.alkemy.ong.infrastructure.config.spring.security.filter;

import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class ForbiddenResponseFilter {

  private static final int STATUS_CODE = HttpStatus.FORBIDDEN.value();
  private static final String MESSAGE = "Access denied.";
  private static final List<String> MORE_INFO = List.of(
      "Access Denied. Contact your administrator.");

  @Autowired
  private ObjectMapper mapper;

  protected void createCustomResponse(HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(STATUS_CODE);

    response.getWriter()
        .write(mapper.writeValueAsString(new ErrorResponse(STATUS_CODE, MESSAGE, MORE_INFO)));
  }

}
