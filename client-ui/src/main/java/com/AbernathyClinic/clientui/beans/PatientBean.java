package com.AbernathyClinic.clientui.beans;

import java.util.Date;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PatientBean {

  private int id;
  private String family;
  private String given;
  @PastOrPresent(message = "The date of birth must be in the past")
  private Date dob;
  @Pattern(message = "The sex must be either M or F", regexp = "^[MF]$")
  private String sex;
  private String address;
  @Length(min = 12, max = 12, message = "Phone number must have 12 character")
  private String phone;
}
