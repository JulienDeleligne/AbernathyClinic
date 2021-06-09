package com.abernathyclinic.noteservice;

import com.abernathyclinic.noteservice.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableWebMvc
public class NoteServiceApplication implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceApplication.class);

  @Autowired
  private InitService initService;

  public static void main(String[] args) {
    SpringApplication.run(NoteServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {
    LOGGER.info("Load data from init");
    LOGGER.info("-------------------------------");
    initService.init();
    LOGGER.info("Datas are loaded successfully.");
  }
}
