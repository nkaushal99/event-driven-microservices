package com.nikhil.datasource.to.kafka.service;

import com.nikhil.config.DatasourceToKafkaServiceConfigData;
import com.nikhil.datasource.to.kafka.service.runner.StreamRunner;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nikhil")
@RequiredArgsConstructor
@Slf4j
public class DatasourceToKafkaServiceApplication implements CommandLineRunner {

  private final DatasourceToKafkaServiceConfigData datasourceToKafkaServiceConfigData;

  private final StreamRunner streamRunner;

  public static void main(String[] args) {
    SpringApplication.run(DatasourceToKafkaServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("App starts...");
    log.info(Arrays.toString(datasourceToKafkaServiceConfigData.getDatasourceKeywords().toArray(new String[]{})));
    log.info(datasourceToKafkaServiceConfigData.getWelcomeMessage());
    streamRunner.start();
  }
}
