package com.abernathyclinic.clientui.beans;

import lombok.Data;

@Data
public class NoteBean {

  private String id;
  private Integer patId;
  private String patient;
  private String recommendations;

}
