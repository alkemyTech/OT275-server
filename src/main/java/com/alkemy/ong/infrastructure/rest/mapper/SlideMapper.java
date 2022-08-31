package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public ListSlideResponse toResponse(List<Slide> slides) {
    if (slides == null || slides.isEmpty()) {
      return new ListSlideResponse(Collections.emptyList());
    }

    List<SlideResponse> slideResponses = slides.stream()
        .map(slide -> new SlideResponse(slide.getImageUrl(), slide.getOrder()))
        .collect(Collectors.toCollection(() -> new ArrayList<>(slides.size())));

    return new ListSlideResponse(slideResponses);
  }
}
