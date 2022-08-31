package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.repository.ActivityRepository;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActivitySeeder {

  private final ActivityRepository activityRepository;


  private ArrayList<ActivityEntity> getActivities() {
    ArrayList<ActivityEntity> activities = new ArrayList<>(3);

    activities.add(new ActivityEntity("Elementary School",
        "Elementary School school-based support programmes",
        "s3.amazonaws.com/bucket/path/image.png"));

    activities.add(new ActivityEntity("High School",
        "High School school-based support programmes",
        "s3.amazonaws.com/bucket/path/image2.png"));

    activities.add(new ActivityEntity("Lessons",
        "Math and English lessons",
        "s3.amazonaws.com/bucket/path/image3.png"));

    return activities;
  }

  @EventListener()
  public void seed(ContextRefreshedEvent event) {
    if (activityRepository.count() == 0) {
      activityRepository.saveAll(getActivities());
    }
  }

}
