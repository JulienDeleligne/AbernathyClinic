package com.abernathyclinic.noteservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Builder
public class NoteDto {

  private int id;
  private int patId;
  private String patient;
  private String recommendations;

}

