package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.mapper.GetOrganizationMapper;
import com.alkemy.ong.infrastructure.rest.mapper.OrganizationUpdateMapper;
import com.alkemy.ong.infrastructure.rest.request.OrganizationUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.GetOrganizationResponse;
import com.alkemy.ong.infrastructure.rest.response.OrganizationUpdateResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationResource {

  private final IGetOrganizationUseCase getOrganizationUseCaseService;
  private final IUpdateOrganizationUseCase updateOrganizationUseCase;
  private final GetOrganizationMapper getOrganizationMapper;
  private final OrganizationUpdateMapper organizationUpdateMapper;

  @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetOrganizationResponse> get() {
    Organization organization = getOrganizationUseCaseService.get();
    return new ResponseEntity<>(getOrganizationMapper.toResponse(organization), HttpStatus.OK);
  }

  @PatchMapping(
      value = "/public",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrganizationUpdateResponse> patch(
      @Valid @RequestBody OrganizationUpdateRequest updateRequest) {
    Organization updatedOrganization = updateOrganizationUseCase.update(
        organizationUpdateMapper.toDomain(updateRequest));
    return new ResponseEntity<>(organizationUpdateMapper.toResponse(updatedOrganization),
        HttpStatus.OK);
  }
}
