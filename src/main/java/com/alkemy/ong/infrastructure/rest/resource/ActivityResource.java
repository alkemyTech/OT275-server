package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.rest.mapper.UpdateActivityMapper;
import com.alkemy.ong.infrastructure.rest.request.UpdateActivityRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateActivityResponse;
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
  private final UpdateActivityMapper updateActivityMapper;

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateActivityResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateActivityRequest updateActivityRequest) {
    Activity activity = updateActivityMapper.toDomain(() -> id, updateActivityRequest);
    Activity updatedActivity = updateActivityUseCase.update(activity);
    return ResponseEntity.ok(updateActivityMapper.toResponse(updatedActivity));
  }

}
