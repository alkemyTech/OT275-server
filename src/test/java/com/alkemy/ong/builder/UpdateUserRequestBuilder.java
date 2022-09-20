package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.user.UpdateUserRequest;

public class UpdateUserRequestBuilder {

  public static UpdateUserRequest defaultRequest(){

    UpdateUserRequest updateUserRequest= new UpdateUserRequest();
    updateUserRequest.setFirstName("Mariano");
    updateUserRequest.setLastName("Toranzo");
    updateUserRequest.setImageUrl("https://s3.com/default-image.jpg");
    updateUserRequest.setPassword("abcd12345");
    return updateUserRequest;
  }


    public static UpdateUserRequest buildRequest(String firstName, String lastName,
        String imageUrl,String password){

      UpdateUserRequest updateUserRequest = defaultRequest();
      updateUserRequest.setFirstName(firstName);
      updateUserRequest.setLastName(lastName);
      updateUserRequest.setImageUrl(imageUrl);
      updateUserRequest.setPassword(password);
      return updateUserRequest;
    }
}
