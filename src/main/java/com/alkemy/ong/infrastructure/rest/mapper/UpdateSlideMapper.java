package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.request.UpdateSlideRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateSlideResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateSlideMapper {

  public Slide toDomain(Identifiable<Long> identifiable, UpdateSlideRequest updateSlideRequest) {
    if (updateSlideRequest == null) {
      return null;
    }
    Slide slide = new Slide();
    slide.setId(identifiable.getId());
    slide.setText(updateSlideRequest.getText());
    slide.setOrder(updateSlideRequest.getOrder());
    return slide;
  }

  public UpdateSlideResponse toResponse(Slide slide) {
    if (slide == null) {
      return null;
    }
    UpdateSlideResponse updateSlideResponse = new UpdateSlideResponse();
    updateSlideResponse.setText(slide.getText());
    updateSlideResponse.setOrder(slide.getOrder());
    return updateSlideResponse;
  }
}