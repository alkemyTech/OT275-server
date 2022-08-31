package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IActivitySpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ActivityRepository implements IActivityRepository {

  private final IActivitySpringRepository activitySpringRepository;

  public void save(ActivityEntity activityEntity) {
    activitySpringRepository.save(activityEntity);
  }

  public Boolean existSeed(String activityName) {
    return activitySpringRepository.findByName(activityName).isPresent();
  }

}