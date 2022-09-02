package com.alkemy.ong.infrastructure.config.aws.delegate;

import com.alkemy.ong.application.service.delegate.IUploadImage;
import com.alkemy.ong.infrastructure.config.aws.AwsConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ImageDelegate implements IUploadImage {

  private final AwsConfig awsConfig;

  public String upload(InputStream content, String contentType, String fileName) {
    AmazonS3 awsClient = awsConfig.generateS3client();
    String bucketName = awsConfig.getBucketName();
    ObjectMetadata objectMetadata = buildObjectMetadata(contentType);
    PutObjectRequest putObjectRequest = buildPutObjectRequest(
        bucketName,
        content,
        fileName,
        objectMetadata,
        awsClient);
    awsClient.putObject(putObjectRequest);
    return getObjectUrl(awsClient, bucketName, fileName);
  }

  private ObjectMetadata buildObjectMetadata(String contentType) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(contentType);
    return objectMetadata;
  }

  private PutObjectRequest buildPutObjectRequest(String bucketName,
      InputStream inputStream,
      String fileName,
      ObjectMetadata objectMetadata, AmazonS3 awsClient) {
    PutObjectRequest putObjectRequest = new PutObjectRequest(
        bucketName,
        fileName,
        inputStream,
        objectMetadata);
    putObjectRequest.withAccessControlList(awsClient.getBucketAcl(bucketName));
    return putObjectRequest;
  }

  public String getObjectUrl(AmazonS3 awsClient, String bucketName, String fileName) {
    return awsClient.getUrl(bucketName, fileName).toString();
  }
}