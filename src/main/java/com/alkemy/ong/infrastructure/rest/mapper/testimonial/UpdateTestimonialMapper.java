package com.alkemy.ong.infrastructure.rest.mapper.testimonial;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.request.testimonial.UpdateTestimonialRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateTestimonialMapper {

  public Testimonial toDomain(Identifiable<Long> identifiable,
      UpdateTestimonialRequest testimonialRequest) {
    if (testimonialRequest == null) {
      return null;
    }
    Testimonial testimonial = new Testimonial();
    testimonial.setId(identifiable.getId());
    testimonial.setName(testimonialRequest.getName());
    testimonial.setContent(testimonialRequest.getContent());
    testimonial.setImageUrl(testimonialRequest.getImage());
    return testimonial;
  }

}
