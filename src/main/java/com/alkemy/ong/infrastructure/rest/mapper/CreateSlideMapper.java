package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.request.CreateSlideRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateSlideMapper extends GetSlideMapper {

  public Slide toDomain(CreateSlideRequest createSlideRequest) {
    if (createSlideRequest == null) {
      return null;
    }

    Slide slide = new Slide();
    slide.setOrder(createSlideRequest.getOrder());
    slide.setText(createSlideRequest.getText());
    slide.setBase64FileEncoded(createSlideRequest.getBase64FileEncoded());
    slide.setContentType(createSlideRequest.getContentType());
    return slide;
  }

}
