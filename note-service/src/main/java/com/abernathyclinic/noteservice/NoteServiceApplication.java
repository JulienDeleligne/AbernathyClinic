package com.abernathyclinic.noteservice;

import com.abernathyclinic.noteservice.model.Note;
import com.abernathyclinic.noteservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class NoteServiceApplication implements CommandLineRunner {

  @Autowired
  private NoteRepository noteRepository;

  public static void main(String[] args) {
    SpringApplication.run(NoteServiceApplication.class, args);
  }

  @Override
  public void run(String... args) {

    noteRepository.deleteAll();
    noteRepository.save(Note.builder().id("1").patId(1).patient("Smith")
        .recommendations("Patient states that they are 'feeling terrific' Weight at or below recommended level")
        .build());
    noteRepository.save(Note.builder().id("2").patId(1).patient("Smith")
        .recommendations(
            "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late")
        .build());
    noteRepository.save(Note.builder().id("3").patId(2).patient("Jonson")
        .recommendations("Patient states that they are 'feeling terrific' Weight at or below recommended level")
        .build());
    noteRepository.save(Note.builder().id("4").patId(2).patient("Jonson")
        .recommendations(
            "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late")
        .build());
  }
}
