package com.alkemy.ong.application.util;

public interface IMailSender {

  void send(IMail mail);

  MailService getService();

}