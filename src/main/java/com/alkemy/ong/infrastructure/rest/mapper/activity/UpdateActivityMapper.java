package com.alkemy.ong.infrastructure.rest.mapper.activity;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.rest.request.activity.UpdateActivityRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateActivityResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateActivityMapper {

  public Activity toDomain(Identifiable<Long> identifiable,
      UpdateActivityRequest updateActivityRequest) {
    if (updateActivityRequest == null) {
      return null;
    }
    Activity activity = new Activity();
    activity.setId(identifiable.getId());
    activity.setName(updateActivityRequest.getName());
    activity.setImageUrl(updateActivityRequest.getImageUrl());
    activity.setContent(updateActivityRequest.getContent());
    return activity;
  }

  public UpdateActivityResponse toResponse(Activity activity) {
    if (activity == null) {
      return null;
    }
    UpdateActivityResponse updateActivityResponse = new UpdateActivityResponse();
    updateActivityResponse.setId(activity.getId());
    updateActivityResponse.setName(activity.getName());
    updateActivityResponse.setContent(activity.getContent());
    updateActivityResponse.setImageUrl(activity.getImageUrl());
    return updateActivityResponse;
  }

}
