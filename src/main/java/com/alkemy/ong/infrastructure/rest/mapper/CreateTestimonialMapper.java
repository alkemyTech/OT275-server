package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.request.CreateTestimonialRequest;
import com.alkemy.ong.infrastructure.rest.response.CreateTestimonialResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CreateTestimonialMapper {

  Testimonial toDomain(@Valid CreateTestimonialRequest testimonialRequest) {
    if (testimonialRequest == null) {
      return null;
    }
    Testimonial testimonial = new Testimonial();
    testimonial.setName(testimonialRequest.getName());
    testimonial.setContent(testimonialRequest.getContent());
    testimonial.setImageUrl(testimonialRequest.getContent());
    return testimonial;
  }


  CreateTestimonialResponse toResponse(Testimonial testimonial) {
    if (testimonial == null) {
      return null;
    }
    CreateTestimonialResponse testimonialResponse = new CreateTestimonialResponse();
    testimonialResponse.setId(testimonial.getId());
    testimonialResponse.setContent(testimonial.getContent());
    testimonialResponse.setImageUrl(testimonial.getImageUrl());
    testimonialResponse.setName(testimonial.getName());
    return testimonialResponse;
  }

}
