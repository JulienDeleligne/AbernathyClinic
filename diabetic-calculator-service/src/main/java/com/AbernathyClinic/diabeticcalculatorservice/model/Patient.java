package com.AbernathyClinic.diabeticcalculatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

  private String family;
  private String given;
  private Integer age;
  private String result;

  @JsonIgnore
  private String sex;
}
