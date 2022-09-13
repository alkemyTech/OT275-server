package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.CreateActivityRequest;

public class CreateActivityRequestBuilder {

  public static CreateActivityRequest buildRequest(String name, String content, String image) {
    CreateActivityRequest createActivityRequest = new CreateActivityRequest();
    createActivityRequest.setName(name);
    createActivityRequest.setContent(content);
    createActivityRequest.setImageUrl(image);
    return createActivityRequest;
  }

}
