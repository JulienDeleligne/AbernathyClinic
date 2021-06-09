package com.AbernathyClinic.diabeticcalculatorservice.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteBean {

  private String id;
  private Integer patId;
  private String patient;
  private String recommendations;

}
