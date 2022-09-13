package com.alkemy.ong.application.service.activity;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.application.service.activity.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateActivityUseCaseService implements IUpdateActivityUseCase {

  private final IActivityRepository activityRepository;

  @Override
  public Activity update(Activity activity) {
    if (!activityRepository.exists(activity::getId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Activity"));
    }
    return activityRepository.update(activity);
  }

}
