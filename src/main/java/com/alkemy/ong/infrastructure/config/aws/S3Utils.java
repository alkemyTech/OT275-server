package com.alkemy.ong.infrastructure.config.aws;

import com.alkemy.ong.application.util.image.IImage;
import com.alkemy.ong.application.util.image.IImageUploader;
import com.alkemy.ong.application.util.image.ImageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class S3Utils implements IImageUploader {

  private final AwsConfig awsConfig;

  public String upload(IImage image) {
    AmazonS3 awsClient = awsConfig.init();
    PutObjectRequest putObjectRequest = buildPutObjectRequest(image);
    awsClient.putObject(putObjectRequest);
    return awsClient.getUrl(awsConfig.getBucketName(), putObjectRequest.getKey()).toString();
  }

  private PutObjectRequest buildPutObjectRequest(IImage image) {
    ObjectMetadata objectMetadata = buildObjectMetadata(image);
    return new PutObjectRequest(
        awsConfig.getBucketName(),
        image.getFileName(),
        image.getContent(),
        objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
  }

  private ObjectMetadata buildObjectMetadata(IImage object) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(object.getContentType());
    return objectMetadata;
  }

  @Override
  public ImageService getService() {
    return ImageService.AWS;
  }
}