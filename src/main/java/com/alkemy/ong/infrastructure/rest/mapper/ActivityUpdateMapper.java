package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.rest.request.ActivityUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.ActivityUpdateResponse;
import org.springframework.stereotype.Component;

@Component
public class ActivityUpdateMapper {

  public Activity toDomain(Identifiable<Long> identifiable,
      ActivityUpdateRequest activityUpdateRequest) {
    if (activityUpdateRequest == null) {
      return null;
    }
    Activity activity = new Activity();
    activity.setId(identifiable.getId());
    activity.setName(activityUpdateRequest.getName());
    activity.setImageUrl(activityUpdateRequest.getImageUrl());
    activity.setContent(activityUpdateRequest.getContent());
    return activity;
  }

  public ActivityUpdateResponse toResponse(Activity activity) {
    if (activity == null) {
      return null;
    }
    ActivityUpdateResponse activityUpdateResponse = new ActivityUpdateResponse();
    activityUpdateResponse.setId(activity.getId());
    activityUpdateResponse.setName(activity.getName());
    activityUpdateResponse.setContent(activity.getContent());
    activityUpdateResponse.setImageUrl(activity.getImageUrl());
    return activityUpdateResponse;
  }

}
