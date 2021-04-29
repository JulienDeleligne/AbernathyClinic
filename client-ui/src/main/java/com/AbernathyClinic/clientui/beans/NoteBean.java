package com.abernathyclinic.clientui.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteBean {

  private Integer id;
  private Integer patId;
  private String patient;
  private String recommendations;

}
