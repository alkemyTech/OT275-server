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
        .map(slideEntity -> new Slide(slideEntity.getImageUrl(), slideEntity.getPosition()))
        .collect(Collectors.toCollection(() -> new ArrayList<>(slideEntities.size())));
  }
}