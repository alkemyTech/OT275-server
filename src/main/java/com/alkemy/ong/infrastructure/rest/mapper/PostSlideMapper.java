package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.request.SlideRequest;
import com.alkemy.ong.infrastructure.rest.response.SlideWithTextResponse;
import org.springframework.stereotype.Component;

@Component
public class PostSlideMapper {

  public Slide toDomain(SlideRequest slideRequest) {
    if (slideRequest == null) {
      return null;
    }

    Slide slide = new Slide();
    slide.setOrder(slideRequest.getOrder());
    slide.setText(slideRequest.getText());
    slide.setBase64FileEncoded(slideRequest.getBase64FileEncoded());

    return slide;
  }

  public SlideWithTextResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    return new SlideWithTextResponse(slide.getImageUrl(), slide.getOrder(), slide.getText());
  }

}
