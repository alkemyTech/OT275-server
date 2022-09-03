package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.rest.mapper.ActivityUpdateMapper;
import com.alkemy.ong.infrastructure.rest.request.ActivityUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.ActivityUpdateResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("activities")
public class ActivityResource {

  private final IUpdateActivityUseCase updateActivityUseCase;

  private final ActivityUpdateMapper activityUpdateMapper;

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ActivityUpdateResponse> update(@PathVariable Long id,
      @Valid @RequestBody ActivityUpdateRequest activityUpdateRequest) {
    Activity activity = activityUpdateMapper.toDomain(() -> id, activityUpdateRequest);
    Activity updatedActivity = updateActivityUseCase.update(activity);
    return ResponseEntity.ok(activityUpdateMapper.toResponse(updatedActivity));
  }

}
