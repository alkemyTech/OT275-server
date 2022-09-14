package com.alkemy.ong.infrastructure.rest.mapper.news;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.response.news.ListNewsResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListNewsMapper {

  private final GetNewsMapper getNewsMapper;

  public ListNewsResponse toResponse(List<News> news) {
    if (news == null || news.isEmpty()) {
      return new ListNewsResponse(Collections.emptyList());
    }
    return new ListNewsResponse(getNewsMapper.toResponse(news));
  }

  public ListNewsResponse toResponse(Page<News> news) {
    ListNewsResponse listNewsResponse = toResponse(news.getContent());
    listNewsResponse.setSize(news.getSize());
    listNewsResponse.setPage(news.getNumber());
    listNewsResponse.setTotalPages(news.getTotalPages());
    return listNewsResponse;
  }

}
