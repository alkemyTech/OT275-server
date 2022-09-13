package com.alkemy.ong.infrastructure.rest.mapper.slide;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.slide.GetSlideResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSlideMapper {

  public GetSlideResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    GetSlideResponse getSlideResponse = new GetSlideResponse();
    getSlideResponse.setId(slide.getId());
    getSlideResponse.setImageUrl(slide.getImageUrl());
    getSlideResponse.setText(slide.getText());
    getSlideResponse.setPosition(slide.getOrder());
    return getSlideResponse;
  }

}
