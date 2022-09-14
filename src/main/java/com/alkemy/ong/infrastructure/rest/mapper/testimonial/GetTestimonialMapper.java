package com.alkemy.ong.infrastructure.rest.mapper.testimonial;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.response.testimonial.GetTestimonialResponse;
import org.springframework.stereotype.Component;

@Component
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

}
