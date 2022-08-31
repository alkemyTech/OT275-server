package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.repository.ActivityRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;


public class Seeder {

  /*
    @Override
    public void run(String... args) throws Exception {
      System.out.println("Hola");
    }

   */
  @Autowired
  ActivityRepository activityRepository;

  private List<ActivityEntity> activities;

  private void addToList() {
    activities = new ArrayList<>();
    activities.add(new ActivityEntity("Elementary School",
        "Elementary School school-based support programmes", "01"));
    activities.add(new ActivityEntity("High School",
        "High School school-based support programmes", "01"));
    activities.add(new ActivityEntity("Lessons", "Math and English lessons",
        "01"));
  }

  @EventListener
  public void seed(ContextStartedEvent asd) {
    for (ActivityEntity a : activities) {
      if (!activityRepository.existSeed(a.getName())) {
        activityRepository.save(a);
      }
    }
  }
}
