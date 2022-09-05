package com.alkemy.ong.application.util.mail;

import java.io.IOException;

public interface IMail {

  String getSubject();

  String getTo();

  String getContent() throws IOException;

  String getContentType();

}
