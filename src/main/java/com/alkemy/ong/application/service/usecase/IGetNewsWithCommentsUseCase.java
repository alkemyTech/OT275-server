package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;

public interface IGetNewsWithCommentsUseCase {

  News get(Identifiable<Long> identifiable);

}
