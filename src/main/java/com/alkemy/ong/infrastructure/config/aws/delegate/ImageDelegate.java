package com.alkemy.ong.infrastructure.config.aws.delegate;

import com.alkemy.ong.application.service.delegate.IUploadImage;
import com.alkemy.ong.infrastructure.config.aws.AwsConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ImageDelegate implements IUploadImage {

  private final AwsConfig awsConfig;

  private static final String HTTPS_PART = "https://";
  private static final String AMAZON_PART = ".s3.amazonaws.com/";

  public String upload(InputStream content, String contentType, String fileName) {
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(contentType);
    AmazonS3 awsClient = awsConfig.generateS3client();
    awsClient.putObject(
        awsConfig.getBucketName(),
        fileName,
        content,
        metadata);
    return buildObjectUrl(fileName);
  }

  private String buildObjectUrl(String fileName) {
    return HTTPS_PART + awsConfig.getBucketName() + AMAZON_PART + fileName;
  }
}