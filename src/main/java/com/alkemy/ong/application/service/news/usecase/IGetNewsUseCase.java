package com.alkemy.ong.application.service.news.usecase;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;

public interface IGetNewsUseCase {

  News get(Identifiable<Long> identifiable);

}
