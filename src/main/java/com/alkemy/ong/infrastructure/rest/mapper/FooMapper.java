package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Foo;
import com.alkemy.ong.infrastructure.rest.response.FooResponse;
import com.alkemy.ong.infrastructure.rest.response.ListFooResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FooMapper {

  public ListFooResponse toResponse(List<Foo> foos) {
    if (foos == null || foos.isEmpty()) {
      return new ListFooResponse(Collections.emptyList());
    }

    List<FooResponse> fooResponses = new ArrayList<>(foos.size());
    for (Foo foo : foos) {
      fooResponses.add(new FooResponse(foo.getName()));
    }

    return new ListFooResponse(fooResponses);
  }
}
