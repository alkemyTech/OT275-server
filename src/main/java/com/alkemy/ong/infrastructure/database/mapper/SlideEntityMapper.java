package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SlideEntityMapper {

  public List<Slide> toDomain(List<SlideEntity> slideEntities) {
    if (slideEntities.isEmpty() || slideEntities == null) {
      return Collections.emptyList();
    }

    return slideEntities.stream()
        .map(slideEntity -> {
          Slide slide = new Slide();
          slide.setImageUrl(slideEntity.getImageUrl());
          slide.setOrder(slideEntity.getPosition());
          return slide;
        })
        .collect(Collectors.toCollection(() -> new ArrayList<>(slideEntities.size())));
  }

  public Slide toDomain(SlideEntity slideEntity) {
    if (slideEntity == null) {
      return null;
    }
    Slide slide = new Slide();
    slide.setId(slideEntity.getSlideId());
    slide.setImageUrl(slideEntity.getImageUrl());
    slide.setOrder(slideEntity.getPosition());
    slide.setText(slideEntity.getText());
    return slide;
  }

}