package com.alkemy.ong.infrastructure.rest.mapper.slide;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.slide.SlideResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public List<SlideResponse> toResponse(List<Slide> slides) {
    if (slides == null || slides.isEmpty()) {
      return Collections.emptyList();
    }
    List<SlideResponse> slideResponses = new ArrayList<>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(toResponse(slide));
    }
    return slideResponses;
  }

  public SlideResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    return new SlideResponse(slide.getImageUrl(), slide.getOrder());
  }
}
