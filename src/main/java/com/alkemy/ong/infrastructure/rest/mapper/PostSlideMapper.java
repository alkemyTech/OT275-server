package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.request.CreateSlideRequest;
import com.alkemy.ong.infrastructure.rest.response.SlideWithTextResponse;
import org.springframework.stereotype.Component;

@Component
public class PostSlideMapper {

  public Slide toDomain(CreateSlideRequest createSlideRequest) {
    if (createSlideRequest == null) {
      return null;
    }

    Slide slide = new Slide();
    slide.setOrder(createSlideRequest.getOrder());
    slide.setText(createSlideRequest.getText());
    slide.setBase64FileEncoded(createSlideRequest.getBase64FileEncoded());

    return slide;
  }

  public SlideWithTextResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    return new SlideWithTextResponse(slide.getImageUrl(), slide.getOrder(), slide.getText());
  }

}
