package com.alkemy.ong.application.util;

import java.io.IOException;

public interface IMailSender {

  void send(IMail mail) throws IOException;

  MailService getService();

}