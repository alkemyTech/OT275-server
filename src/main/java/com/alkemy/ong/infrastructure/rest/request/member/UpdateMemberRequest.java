package com.alkemy.ong.infrastructure.rest.request.member;

import com.alkemy.ong.infrastructure.rest.request.common.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithoutWhiteSpaces;
import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemberRequest {

  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  @NotEmpty(message = "Image cannot be empty.")
  @AlphanumericWithoutWhiteSpaces(message = "Image must be alphanumeric without white spaces.")
  private String image;

  private String description;

  private SocialMediaRequest socialMedia;

}