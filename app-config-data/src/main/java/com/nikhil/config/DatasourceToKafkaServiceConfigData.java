package com.nikhil.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "datasource-to-kafka-service")
public class DatasourceToKafkaServiceConfigData {

  private List<String> datasourceKeywords;
  private String welcomeMessage;
  private Boolean enableMockData;
  private Long mockSleepMs;
  private Integer mockMinTweetLength;
  private Integer mockMaxTweetLength;
}
