package com.alkemy.ong.infrastructure.rest.mapper.activity;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.rest.request.activity.CreateActivityRequest;
import com.alkemy.ong.infrastructure.rest.response.activity.CreateActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateActivityMapper {

  public Activity toDomain(CreateActivityRequest createActivityRequest) {
    if (createActivityRequest == null) {
      return null;
    }
    Activity activity = new Activity();
    activity.setName(createActivityRequest.getName());
    activity.setContent(createActivityRequest.getContent());
    activity.setImageUrl(createActivityRequest.getImageUrl());
    return activity;
  }

  public CreateActivityResponse toResponse(Activity activity) {
    if (activity == null) {
      return null;
    }

    CreateActivityResponse createActivityResponse = new CreateActivityResponse();
    createActivityResponse.setId(activity.getId());
    createActivityResponse.setName(activity.getName());
    createActivityResponse.setContent(activity.getContent());
    createActivityResponse.setImageUrl(activity.getImageUrl());
    return createActivityResponse;

  }

}
