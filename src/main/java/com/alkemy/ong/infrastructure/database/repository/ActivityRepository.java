package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.mapper.ActivityEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IActivitySpringRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ActivityRepository implements IActivityRepository {

  private final IActivitySpringRepository activitySpringRepository;

  private final ActivityEntityMapper activityEntityMapper;

  public void saveAll(List<ActivityEntity> activities) {
    activitySpringRepository.saveAll(activities);
  }

  public long count() {
    return activitySpringRepository.count();
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return activitySpringRepository.exists(identifiable.getId()).isPresent();
  }

  @Override
  public Activity update(Activity activity) {
    ActivityEntity activityEntity = activityEntityMapper.toEntity(activity);
    return activityEntityMapper.toDomain(activitySpringRepository.save(activityEntity));
  }

  @Override
  public Activity save(Activity activity) {
    ActivityEntity activityEntity = activityEntityMapper.toEntity(activity);
    activityEntity.setSoftDeleted(false);
    return activityEntityMapper.toDomain(activitySpringRepository.save(activityEntity));
  }

}