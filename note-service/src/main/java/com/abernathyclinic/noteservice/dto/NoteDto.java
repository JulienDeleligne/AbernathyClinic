package com.abernathyclinic.noteservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteDto {

  private String id;
  private Integer patId;
  private String patient;
  private String recommendations;

}

