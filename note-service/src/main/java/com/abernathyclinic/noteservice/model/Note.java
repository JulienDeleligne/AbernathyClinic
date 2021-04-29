package com.abernathyclinic.noteservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

  @Id
  private String id;
  private Integer patId;
  private String patient;
  private String recommendations;
}
