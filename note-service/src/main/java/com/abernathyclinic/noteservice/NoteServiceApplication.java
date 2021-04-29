package com.abernathyclinic.noteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class NoteServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(NoteServiceApplication.class, args);
  }

}
