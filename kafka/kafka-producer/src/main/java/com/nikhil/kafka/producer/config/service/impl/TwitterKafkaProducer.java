package com.nikhil.kafka.producer.config.service.impl;

import com.nikhil.kafka.avro.model.TwitterAvroModel;
import com.nikhil.kafka.producer.config.service.KafkaProducer;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class TwitterKafkaProducer implements KafkaProducer<Long, TwitterAvroModel> {

  private KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate;

  @Override
  public void send(String topicName, Long key, TwitterAvroModel message) {
    log.info("Sending message='{}' to topic='{}'", message, topicName);
    ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture =
        kafkaTemplate.send(topicName, key, message);
    addCallback(topicName, message, kafkaResultFuture);
  }

  @PreDestroy
  public void close() {
    if (kafkaTemplate != null) {
      log.info("Closing kafka producer!");
      kafkaTemplate.destroy();
    }
  }

  private void addCallback(String topicName, TwitterAvroModel message,
      ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture) {
    kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
      @Override
      public void onFailure(Throwable throwable) {
        log.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
      }

      @Override
      public void onSuccess(SendResult<Long, TwitterAvroModel> result) {
        RecordMetadata metadata = result.getRecordMetadata();
        log.debug("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
            metadata.topic(),
            metadata.partition(),
            metadata.offset(),
            metadata.timestamp(),
            System.nanoTime());
      }
    });
  }
}
