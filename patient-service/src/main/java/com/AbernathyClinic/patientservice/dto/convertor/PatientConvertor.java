package com.abernathyclinic.patientservice.dto.convertor;

import com.abernathyclinic.patientservice.dto.PatientDto;
import com.abernathyclinic.patientservice.model.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientConvertor {

  private PatientConvertor() {
    throw new IllegalStateException("Utility class");
  }

  public static PatientDto convertToPatientDto(Patient patient) {
    return PatientDto.builder().id(patient.getId()).family(patient.getFamily()).given(patient.getGiven())
        .dob(patient.getBirthdate().toString())
        .sex(patient.getSex()).address(patient.getAddress()).phone(patient.getPhone()).build();
  }

  public static Patient convertToPatient(PatientDto patientDto) {
    return Patient.builder().id(patientDto.getId()).family(patientDto.getFamily()).given(patientDto.getGiven())
        .birthdate(
            LocalDate.parse(patientDto.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        .sex(patientDto.getSex()).address(patientDto.getAddress()).phone(patientDto.getPhone()).build();
  }
}
