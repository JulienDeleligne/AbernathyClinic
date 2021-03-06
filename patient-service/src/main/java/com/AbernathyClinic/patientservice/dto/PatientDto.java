package com.abernathyclinic.patientservice.dto;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

  private int id;
  private String family;
  private String given;
  @PastOrPresent(message = "The date of birth must be in the past")
  private String dob;
  @Pattern(message = "The sex must be either M or F", regexp = "^[MF]$")
  private String sex;
  private String address;
  @Length(min = 12, max = 12, message = "Phone number must have 12 character")
  private String phone;
}

