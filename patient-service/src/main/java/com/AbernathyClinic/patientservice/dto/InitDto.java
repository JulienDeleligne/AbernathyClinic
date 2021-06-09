package com.abernathyclinic.patientservice.dto;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent the format for the init
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitDto {

  @Valid
  private List<PatientDto> patients;
}
