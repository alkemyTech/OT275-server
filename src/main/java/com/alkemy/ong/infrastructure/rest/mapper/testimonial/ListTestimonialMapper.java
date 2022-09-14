package com.alkemy.ong.infrastructure.rest.mapper.testimonial;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.response.testimonial.CreateTestimonialResponse;
import com.alkemy.ong.infrastructure.rest.response.testimonial.ListTestimonialResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListTestimonialMapper {

  private final CreateTestimonialMapper createTestimonialMapper;

  CreateTestimonialResponse toResponse(List<Testimonial> testimonials) {
    if (testimonials == null || testimonials.isEmpty()) {

    }
    return new ListTestimonialResponse(createTestimonialMapper.toResponse(testimonials));
  }

  public ListTestimonialResponse toResponse(Page<Testimonial> testimonials) {
    ListTestimonialResponse listTestimonialResponse = toResponse(testimonials.getContent());
    listTestimonialResponse
        return  listTestimonialResponse;
  }
}
