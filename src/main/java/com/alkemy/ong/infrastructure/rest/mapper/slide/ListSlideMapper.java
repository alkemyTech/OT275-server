package com.alkemy.ong.infrastructure.rest.mapper.slide;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListSlideMapper {

  private final SlideMapper slideMapper;

  public ListSlideResponse toResponse(List<Slide> slides) {
    if (slides == null || slides.isEmpty()) {
      return new ListSlideResponse(Collections.emptyList());
    }
    return new ListSlideResponse(slideMapper.toResponse(slides));
  }
}
