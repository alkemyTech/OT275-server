package com.alkemy.ong.application.service.news.usecase;

import com.alkemy.ong.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IListNewsUseCase {

  Page<News> findAll(Pageable pageable);

}
