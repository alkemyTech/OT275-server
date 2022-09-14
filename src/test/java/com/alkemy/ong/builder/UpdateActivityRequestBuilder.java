package com.alkemy.ong.builder;

import com.alkemy.ong.infrastructure.rest.request.activity.UpdateActivityRequest;

public class UpdateActivityRequestBuilder {

  public static UpdateActivityRequest defaultRequest() {
    return buildRequest(
        "Updated Activity",
        "Updated Activity content",
        "https://s3.com/default-image.jpg");
  }

  public static UpdateActivityRequest buildRequest(String name, String content, String imageUrl) {
    UpdateActivityRequest updateActivityRequest = new UpdateActivityRequest();
    updateActivityRequest.setName(name);
    updateActivityRequest.setContent(content);
    updateActivityRequest.setImageUrl(imageUrl);
    return updateActivityRequest;
  }

}
