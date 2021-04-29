package com.abernathyclinic.noteservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
public class NoteDto {

  private String id;
  private Integer patId;
  private String patient;
  private String recommendations;

}

