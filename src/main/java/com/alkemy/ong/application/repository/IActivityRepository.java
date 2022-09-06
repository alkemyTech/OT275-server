package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.domain.Identifiable;

public interface IActivityRepository {

  boolean exists(Identifiable<Long> identifiable);

  Activity update(Activity activity);

  Activity create(Activity activity);

}
