package com.alkemy.ong.application.service.delegate;

import java.io.InputStream;

public interface IUploadImage {

  String upload(InputStream inputStream, String contentType, String fileName);

}
