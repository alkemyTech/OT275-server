package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.UpdateActivityRequest;

public class UpdateActivityRequestBuilder {

  public static UpdateActivityRequest defaultRequest() {
    UpdateActivityRequest updateActivityRequest = new UpdateActivityRequest();
    updateActivityRequest.setName("My Activity");
    updateActivityRequest.setContent("My Activity content");
    updateActivityRequest.setImageUrl("https://s3.com/default-image.jpg");
    return updateActivityRequest;
  }

  public static UpdateActivityRequest buildRequest(String name, String content, String imageUrl) {
    UpdateActivityRequest updateActivityRequest = defaultRequest();
    updateActivityRequest.setName(name);
    updateActivityRequest.setContent(content);
    updateActivityRequest.setImageUrl(imageUrl);
    return updateActivityRequest;
  }

}
