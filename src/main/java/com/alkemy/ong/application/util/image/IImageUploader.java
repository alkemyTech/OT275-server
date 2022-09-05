package com.alkemy.ong.application.util.image;


public interface IImageUploader {

  String upload(IImage image);

  ImageService getService();

}
