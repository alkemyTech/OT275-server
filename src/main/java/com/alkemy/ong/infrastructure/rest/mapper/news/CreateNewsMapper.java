package com.alkemy.ong.infrastructure.rest.mapper.news;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.category.GetCategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.news.CreateNewsRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateNewsMapper extends GetNewsMapper {

  public CreateNewsMapper(GetCategoryMapper getCategoryMapper) {
    super(getCategoryMapper);
  }

  public News toDomain(CreateNewsRequest newsRequest) {
    if (newsRequest == null) {
      return null;
    }
    News news = new News();
    news.setName(newsRequest.getName());
    news.setContent(newsRequest.getText());
    news.setImageUrl(newsRequest.getImage());
    return news;
  }

}
