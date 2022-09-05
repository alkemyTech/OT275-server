package com.alkemy.ong.application.util.mail;

import lombok.Getter;

@Getter
public enum MailService {

  SENDGRID("SENDGRID");

  private final String serviceName;

  MailService(String serviceName) {
    this.serviceName = serviceName;
  }

}