package com.alkemy.ong.infrastructure.config.spring.security.filter;

import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

  private static final int STATUS_CODE = HttpStatus.FORBIDDEN.value();
  private static final String MESSAGE = "FORBIDDEN";
  private static final List<String> MORE_INFO = List.of(
      "Access Denied. Contact your administrator");

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(STATUS_CODE);

    PrintWriter writer = response.getWriter();
    objectMapper.writeValue(writer,
        new ErrorResponse(STATUS_CODE, MESSAGE, MORE_INFO));
    writer.flush();
  }

}


