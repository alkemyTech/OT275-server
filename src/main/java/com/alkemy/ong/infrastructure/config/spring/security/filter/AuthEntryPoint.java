package com.alkemy.ong.infrastructure.config.spring.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    OutputStream responseStream = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(responseStream,
        new AuthenticationError(new Timestamp(System.currentTimeMillis()),
            request.getServletPath()));
    responseStream.flush();
  }


  private static class AuthenticationError {

    public Timestamp timestamp;
    public final int STATUS_CODE = 403;
    public final String ERROR = "Forbidden";
    public final String MESSAGE = "Invalid Credentials";
    public String path;

    public AuthenticationError(Timestamp timestamp, String path) {
      this.timestamp = timestamp;
      this.path = path;
    }
  }

}


