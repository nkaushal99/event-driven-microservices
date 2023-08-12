package com.nikhil.datasource.to.kafka.service;

import com.nikhil.datasource.to.kafka.service.config.DatasourceToKafkaServiceConfigData;
import com.nikhil.datasource.to.kafka.service.runner.StreamRunner;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nikhil")
public class DatasourceToKafkaServiceApplication implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(DatasourceToKafkaServiceApplication.class);

  private final DatasourceToKafkaServiceConfigData datasourceToKafkaServiceConfigData;

  private final StreamRunner streamRunner;

  public DatasourceToKafkaServiceApplication(DatasourceToKafkaServiceConfigData configData,
      StreamRunner runner) {
    this.datasourceToKafkaServiceConfigData = configData;
    this.streamRunner = runner;
  }

  public static void main(String[] args) {
    SpringApplication.run(DatasourceToKafkaServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {
    LOG.info("App starts...");
    LOG.info(Arrays.toString(datasourceToKafkaServiceConfigData.getDatasourceKeywords().toArray(new String[]{})));
    LOG.info(datasourceToKafkaServiceConfigData.getWelcomeMessage());
    streamRunner.start();
  }
}
