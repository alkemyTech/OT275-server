package com.alkemy.ong.infrastructure.rest.mapper.news;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.category.GetCategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.UpdateNewsRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateNewsMapper extends GetNewsMapper {

  public UpdateNewsMapper(GetCategoryMapper getCategoryMapper) {
    super(getCategoryMapper);
  }

  public News toDomain(Identifiable<Long> identifiable, UpdateNewsRequest updateNewsRequest) {
    if (updateNewsRequest == null) {
      return null;
    }
    News news = new News();
    news.setId(identifiable.getId());
    news.setName(updateNewsRequest.getName());
    news.setContent(updateNewsRequest.getContent());
    news.setImageUrl(updateNewsRequest.getImage());
    return news;
  }

}
