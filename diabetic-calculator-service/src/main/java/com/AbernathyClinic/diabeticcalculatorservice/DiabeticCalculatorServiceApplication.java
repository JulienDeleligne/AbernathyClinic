package com.AbernathyClinic.diabeticcalculatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients("com.AbernathyClinic.diabeticcalculatorservice")
class DiabeticCalculatorServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DiabeticCalculatorServiceApplication.class, args);
  }

}
