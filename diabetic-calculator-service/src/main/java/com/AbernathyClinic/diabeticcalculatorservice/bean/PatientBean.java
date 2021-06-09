package com.AbernathyClinic.diabeticcalculatorservice.bean;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientBean {

  private int id;
  private String family;
  private String given;
  private LocalDate dob;
  private String sex;
  private String address;
  private String phone;
}
