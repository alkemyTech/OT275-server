package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class UpdateOrganizationRequest {

  @Nullable
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  private String imageUrl;

  private String address;

  private String phone;

  @Nullable
  @Email(message = "Email has invalid format.")
  private String email;

  private SocialMediaRequest socialMedia;

  private String aboutUsText;

  @Nullable
  @Length(max = 50, message = "Welcome text length must be shorter than 50 characters.")
  private String welcomeText;

}