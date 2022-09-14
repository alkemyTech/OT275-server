package com.alkemy.ong.infrastructure.rest.mapper.testimonial;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.request.testimonial.CreateTestimonialRequest;
import com.alkemy.ong.infrastructure.rest.response.testimonial.GetTestimonialResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateTestimonialMapper extends GetTestimonialMapper {

  public Testimonial toDomain(CreateTestimonialRequest testimonialRequest) {
    if (testimonialRequest == null) {
      return null;
    }
    Testimonial testimonial = new Testimonial();
    testimonial.setName(testimonialRequest.getName());
    testimonial.setContent(testimonialRequest.getContent());
    testimonial.setImageUrl(testimonialRequest.getImage());
    return testimonial;
  }

}
