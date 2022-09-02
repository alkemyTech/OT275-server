package com.alkemy.ong.application.util;

import java.io.InputStream;

public interface IImage {

  InputStream getInputStream();

  String getContentType();

  String getFileName();

}
