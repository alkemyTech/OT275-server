package com.alkemy.ong.infrastructure.rest.mapper.testimonial;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.response.testimonial.ListTestimonialResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListTestimonialMapper {

  private final GetTestimonialMapper getTestimonialMapper;

  public ListTestimonialResponse toResponse(List<Testimonial> testimonials) {
    if (testimonials == null || testimonials.isEmpty()) {
      return new ListTestimonialResponse(Collections.emptyList());

    }
    return new ListTestimonialResponse(getTestimonialMapper.toResponse(testimonials));
  }

  public ListTestimonialResponse toResponse(Page<Testimonial> testimonials) {
    ListTestimonialResponse listTestimonialResponse = toResponse(testimonials.getContent());
    listTestimonialResponse.setPage(testimonials.getNumber());
    listTestimonialResponse.setSize(testimonials.getSize());
    listTestimonialResponse.setTotalPages(testimonials.getTotalPages());
    return listTestimonialResponse;
  }
}
