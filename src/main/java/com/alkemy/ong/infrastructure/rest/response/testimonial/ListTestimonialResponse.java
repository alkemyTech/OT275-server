package com.alkemy.ong.infrastructure.rest.response.testimonial;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ListTestimonialResponse {

  List<CreateTestimonialResponse> testimonials;

}
