package com.alkemy.ong.application.util;

import lombok.Getter;

@Getter
public enum MailService {

  SENDGRID("SENDGRID", "A");

  private final String serviceName;
  private final String vendor;

  MailService(String serviceName, String vendor) {
    this.serviceName = serviceName;
    this.vendor = vendor;
  }

}