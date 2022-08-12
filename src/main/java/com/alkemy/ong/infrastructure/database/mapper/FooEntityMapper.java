package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Foo;
import com.alkemy.ong.infrastructure.database.entity.FooEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FooEntityMapper {

  public List<Foo> toDomain(List<FooEntity> fooEntities) {
    if (fooEntities == null || fooEntities.isEmpty()) {
      return Collections.emptyList();
    }

    List<Foo> foos = new ArrayList<>(fooEntities.size());
    for (FooEntity fooEntity : fooEntities) {
      foos.add(Foo.builder()
          .name(fooEntity.getName())
          .build());
    }

    return foos;
  }

}
