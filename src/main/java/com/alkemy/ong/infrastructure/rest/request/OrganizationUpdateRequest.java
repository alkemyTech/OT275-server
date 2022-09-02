package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhitespaces;
import javax.validation.constraints.Email;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
public class OrganizationUpdateRequest {

  @Nullable
  @CharactersWithWhitespaces(message = "Name must contain only spaces and letters")
  private String name;

  private String imageUrl;

  private String address;

  private String phone;

  @Nullable
  @Email(message = "Email has invalid format")
  private String email;

  private SocialMediaRequest socialMedia;

  private String aboutUsText;

  @Nullable
  @Length(max = 50, message = "welcome text length must be shorter than 50 characters")
  private String welcomeText;

}