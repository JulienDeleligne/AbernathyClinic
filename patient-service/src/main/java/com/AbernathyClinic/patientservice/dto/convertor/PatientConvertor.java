package com.AbernathyClinic.patientservice.dto.convertor;

import com.AbernathyClinic.patientservice.dto.PatientDto;
import com.AbernathyClinic.patientservice.model.Patient;

public class PatientConvertor {

  public PatientDto convertToPatientDto(Patient patient) {
    return PatientDto.builder().id(patient.getId()).family(patient.getFamily()).given(patient.getGiven()).dob(patient.getBirthdate())
        .sex(patient.getSex())
        .address(patient.getAddress()).phone(patient.getPhone()).build();
  }

  public Patient convertToPatient(PatientDto patientDto) {
    return Patient.builder().family(patientDto.getFamily()).given(patientDto.getGiven()).birthdate(patientDto.getDob())
        .sex(patientDto.getSex())
        .address(patientDto.getAddress()).phone(patientDto.getPhone()).build();
  }
}
