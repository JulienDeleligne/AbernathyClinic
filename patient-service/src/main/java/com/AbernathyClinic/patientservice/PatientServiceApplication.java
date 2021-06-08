package com.abernathyclinic.patientservice;

import com.abernathyclinic.patientservice.dto.InitDto;
import com.abernathyclinic.patientservice.service.InitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableWebMvc
public class PatientServiceApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceApplication.class);

  @Value("classpath:data/data.json")
  private Resource dataResourceFile;

  @Autowired
  private InitService initService;

  public static void main(String[] args) {
    SpringApplication.run(PatientServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      // Load data from data.json at startup of application
      LOGGER.info("Load data from data.json file");
      LOGGER.info("-------------------------------");
      var mapper = new ObjectMapper();
      initService.init(mapper.readValue(dataResourceFile.getFile(), InitDto.class));
      LOGGER.info("Datas are loaded successfully.");
    };
  }

}
