package com.alkemy.ong.application.util;


public interface IImageUploader {

  String upload(IImage image);

  ImageService getService();

}
