package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public SlideResponse toResponse(Slide slide) {
    if(slide == null){
      return null;
    }
    return new SlideResponse(slide.getImageUrl(), slide.getOrder());
  }
}
