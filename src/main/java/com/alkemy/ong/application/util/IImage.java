package com.alkemy.ong.application.util;

import java.io.InputStream;

public interface IImage {

  InputStream getContent();

  String getContentType();

  String getFileName();

}
