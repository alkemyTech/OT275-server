package com.alkemy.ong.infrastructure.rest.mapper.testimonial;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.response.testimonial.GetTestimonialResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTestimonialMapper {

  public GetTestimonialResponse toResponse(Testimonial testimonial) {
    if (testimonial == null) {
      return null;
    }
    GetTestimonialResponse getTestimonialResponse = new GetTestimonialResponse();
    getTestimonialResponse.setId(testimonial.getId());
    getTestimonialResponse.setContent(testimonial.getContent());
    getTestimonialResponse.setImageUrl(testimonial.getImageUrl());
    getTestimonialResponse.setName(testimonial.getName());
    return getTestimonialResponse;
  }

  public List<GetTestimonialResponse> toResponse(List<Testimonial> testimonials) {
    if (testimonials == null || testimonials.isEmpty()) {
      return Collections.emptyList();
    }
    List<GetTestimonialResponse> testimonialResponses = new ArrayList<>(testimonials.size());
    for (Testimonial testimonial : testimonials) {
      testimonialResponses.add(toResponse(testimonial));
    }
    return testimonialResponses;
  }
}
