package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.repository.ActivityRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActivitySeeder {

  ActivityRepository activityRepository;

  private List<ActivityEntity> activities;

  private void addActivitiesToList() {
    activities = new ArrayList<>();

    activities.add(new ActivityEntity("Elementary School",
        "Elementary School school-based support programmes", "01"));

    activities.add(new ActivityEntity("High School",
        "High School school-based support programmes", "01"));

    activities.add(new ActivityEntity("Lessons",
        "Math and English lessons", "01"));
  }

  @EventListener()
  public void seed(ContextRefreshedEvent event) {

    this.addActivitiesToList();

    for (ActivityEntity a : activities) {
      if (!activityRepository.existSeed(a.getName())) {
        activityRepository.save(a);
      }
    }
  }
}
