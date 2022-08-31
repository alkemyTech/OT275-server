package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.mapper.PublicOrganizationMapper;
import com.alkemy.ong.infrastructure.rest.response.PublicOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationResource {

  private final IGetOrganizationUseCase getOrganizationUseCaseService;
 
  private final PublicOrganizationMapper publicOrganizationMapper;

  @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublicOrganizationResponse> get() {
    Organization organization = getOrganizationUseCaseService.get();
    PublicOrganizationResponse publicOrganizationResponse = publicOrganizationMapper
        .toResponse(organization);

    return new ResponseEntity<>(publicOrganizationResponse, HttpStatus.OK);

  }
}
