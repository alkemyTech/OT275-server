package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ActivityRepository implements IActivityRepository {

  private final IActivitySpringRepository activitySpringRepository;

}
