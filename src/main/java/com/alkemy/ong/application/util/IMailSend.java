package com.alkemy.ong.application.util;

public interface IMailSend {

  void send(IMail mail);

  EmailService getServiceName();

}