package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IActivitySpringRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ActivityRepository implements IActivityRepository {

  private final IActivitySpringRepository activitySpringRepository;

  public void saveAll(List<ActivityEntity> activities) {
    activitySpringRepository.saveAll(activities);
  }

  public long count() {
    return activitySpringRepository.count();
  }
}