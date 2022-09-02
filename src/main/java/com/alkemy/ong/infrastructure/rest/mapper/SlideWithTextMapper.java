package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.SlideWithTextResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlideWithTextMapper {

  public List<SlideWithTextResponse> toResponse(List<Slide> slides) {
    if (slides == null) {
      return null;
    }
    List<SlideWithTextResponse> slideWithTextResponseList = new java.util.ArrayList<>();
    for (Slide slide : slides) {
      slideWithTextResponseList.add(toResponse(slide));
    }
    return slideWithTextResponseList;
  }

  public SlideWithTextResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    return new SlideWithTextResponse(slide.getImageUrl(), slide.getOrder(), slide.getText());
  }

}
