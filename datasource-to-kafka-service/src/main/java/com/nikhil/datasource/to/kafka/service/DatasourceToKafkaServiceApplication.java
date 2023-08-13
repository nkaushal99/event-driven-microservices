package com.nikhil.datasource.to.kafka.service;

import com.nikhil.datasource.to.kafka.service.init.StreamInitializer;
import com.nikhil.datasource.to.kafka.service.runner.StreamRunner;
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

  private final StreamRunner streamRunner;

  private final StreamInitializer streamInitializer;

  public static void main(String[] args) {
    SpringApplication.run(DatasourceToKafkaServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("App starts...");
    streamInitializer.init();
    streamRunner.start();
  }
}
