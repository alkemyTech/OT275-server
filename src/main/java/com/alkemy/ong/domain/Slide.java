package com.alkemy.ong.domain;

import com.alkemy.ong.application.util.IImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Slide implements IImage {

  private Long id;
  private String imageUrl;
  private Integer order;
  private String text;
  private String base64FileEncoded;
  private String contentType;

  @Override
  public InputStream getContent() {
    return new ByteArrayInputStream(Base64.getDecoder().decode(base64FileEncoded));
  }

  @Override
  public String getContentType() {
    return this.contentType;
  }

  @Override
  public String getFileName() {
    return UUID.randomUUID().toString();
  }
}
