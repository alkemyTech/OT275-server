package com.alkemy.ong.application.util.mail;

public interface IMailSender {

  void send(IMail mail);

  MailService getService();

}