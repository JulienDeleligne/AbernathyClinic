package com.AbernathyClinic.diabeticcalculatorservice.bean;

import lombok.Data;

@Data
public class NoteBean {

  private String id;
  private Integer patId;
  private String patient;
  private String recommendations;

}
