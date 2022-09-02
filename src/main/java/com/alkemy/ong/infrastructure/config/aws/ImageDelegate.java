package com.alkemy.ong.infrastructure.config.aws;

import com.alkemy.ong.application.util.IImage;
import com.alkemy.ong.application.util.IImageUploader;
import com.alkemy.ong.application.util.ImageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ImageDelegate implements IImageUploader {

  private final AwsConfig awsConfig;

  public String upload(IImage object) {
    AmazonS3 awsClient = awsConfig.generateS3client();
    PutObjectRequest putObjectRequest = buildPutObjectRequest(object, awsClient);
    awsClient.putObject(putObjectRequest);
    return awsClient.getUrl(awsConfig.getBucketName(), putObjectRequest.getKey()).toString();
  }

  private PutObjectRequest buildPutObjectRequest(IImage image, AmazonS3 awsClient) {
    String bucketName = awsConfig.getBucketName();
    ObjectMetadata objectMetadata = buildObjectMetadata(image);
    PutObjectRequest putObjectRequest = new PutObjectRequest(
        bucketName,
        image.getFileName(),
        image.getInputStream(),
        objectMetadata);
    putObjectRequest.withAccessControlList(awsClient.getBucketAcl(bucketName));
    return putObjectRequest;
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