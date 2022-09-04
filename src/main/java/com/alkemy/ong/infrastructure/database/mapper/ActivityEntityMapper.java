package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivityEntityMapper {

  public ActivityEntity toEntity(Activity activity) {
    if (activity == null) {
      return null;
    }
    ActivityEntity activityEntity = new ActivityEntity();
    activityEntity.setActivityId(activity.getId());
    activityEntity.setName(activity.getName());
    activityEntity.setContent(activity.getContent());
    activityEntity.setImageUrl(activity.getImageUrl());
    return activityEntity;
  }

  public Activity toDomain(ActivityEntity activityEntity) {
    if (activityEntity == null) {
      return null;
    }
    Activity activity = new Activity();
    activity.setId(activityEntity.getActivityId());
    activity.setName(activityEntity.getName());
    activity.setContent(activityEntity.getContent());
    activity.setImageUrl(activityEntity.getImageUrl());
    return activity;
  }

}
