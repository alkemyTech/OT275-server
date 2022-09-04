package com.alkemy.ong.application.util;

import lombok.Getter;

@Getter
public enum ImageService {

  AWS("AWS");

  private final String serviceName;

  ImageService(String serviceName) {
    this.serviceName = serviceName;
  }
}
