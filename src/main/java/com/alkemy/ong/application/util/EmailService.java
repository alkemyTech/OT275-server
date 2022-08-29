package com.alkemy.ong.application.util;

import lombok.Getter;

@Getter
public enum EmailService {

  SENDGRID("SENDGRID", "A");

  private final String serviceName;
  private final String vendor;

  EmailService(String serviceName, String vendor) {
    this.serviceName = serviceName;
    this.vendor = vendor;
  }

}