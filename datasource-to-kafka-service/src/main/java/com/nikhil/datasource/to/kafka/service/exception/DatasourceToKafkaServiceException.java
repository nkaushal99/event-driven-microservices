package com.nikhil.datasource.to.kafka.service.exception;

public class DatasourceToKafkaServiceException extends RuntimeException {

  public DatasourceToKafkaServiceException() {
    super();
  }

  public DatasourceToKafkaServiceException(String message) {
    super(message);
  }

  public DatasourceToKafkaServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
