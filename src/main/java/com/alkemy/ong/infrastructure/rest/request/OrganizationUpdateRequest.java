package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.Name;
import javax.validation.constraints.Email;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class OrganizationUpdateRequest {

  @Name(message = "Name must contain only spaces and letters")
  private String name;
  private String imageUrl;
  private String address;
  private String phone;
  @Email(message = "Email has invalid format")
  private String email;
  private String facebookUrl;
  private String linkedInUrl;
  private String instagramUrl;
  private String aboutUsText;
  @Length(max = 50, message = "welcome text length must be shorter than 50 characters")
  private String welcomeText;

}