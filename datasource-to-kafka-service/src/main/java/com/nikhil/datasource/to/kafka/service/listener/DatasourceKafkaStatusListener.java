package com.nikhil.datasource.to.kafka.service.listener;

import com.nikhil.config.KafkaConfigData;
import com.nikhil.datasource.to.kafka.service.transformer.TwitterStatusToAvroTransformer;
import com.nikhil.kafka.avro.model.TwitterAvroModel;
import com.nikhil.kafka.producer.config.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatasourceKafkaStatusListener extends StatusAdapter {

  private final KafkaConfigData kafkaConfigData;

  private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;

  private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

  @Override
  public void onStatus(Status status) {
    log.info("Received status text {} sending to kafka topic {}", status.getText(), kafkaConfigData.getTopicName());
    TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
    kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
  }
}
