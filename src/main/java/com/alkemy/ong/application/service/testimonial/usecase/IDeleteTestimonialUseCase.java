package com.alkemy.ong.application.service.testimonial.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IDeleteTestimonialUseCase {

  void delete(Identifiable<Long> identifiable);

}
