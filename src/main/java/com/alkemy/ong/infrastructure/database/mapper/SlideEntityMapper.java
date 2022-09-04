package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideEntityMapper {

  public List<Slide> toDomain(List<SlideEntity> slideEntities) {
    if (slideEntities == null || slideEntities.isEmpty()) {
      return Collections.emptyList();
    }

    List<Slide> slides = new ArrayList<>(slideEntities.size());
    for (SlideEntity slideEntity : slideEntities) {
      slides.add(toDomain(slideEntity));
    }

    return slides;
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

  public SlideEntity toEntity(Slide slide) {
    if (slide == null) {
      return null;
    }

    SlideEntity entity = new SlideEntity();
    entity.setPosition(slide.getOrder());
    entity.setText(slide.getText());
    entity.setImageUrl(slide.getImageUrl());

    return entity;
  }

}