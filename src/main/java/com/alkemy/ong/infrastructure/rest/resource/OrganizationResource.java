package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.GetOrganizationUseCaseService;
import javax.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
public class OrganizationResource {

  @Autowired
  private final GetOrganizationUseCaseService getOrganizationUseCaseService;

  @GetMapping(value = "/{public}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> getAll()  {
   getOrganizationUseCaseService.getAll(()-> Id);


  }
}
