package com.abernathyclinic.patientservice.dto.convertor;

import com.abernathyclinic.patientservice.dto.PatientDto;
import com.abernathyclinic.patientservice.model.Patient;

public class PatientConvertor {

  public PatientDto convertToPatientDto(Patient patient) {
    return PatientDto.builder().id(patient.getId()).family(patient.getFamily()).given(patient.getGiven())
        .dob(patient.getBirthdate())
        .sex(patient.getSex())
        .address(patient.getAddress()).phone(patient.getPhone()).build();
  }

  public Patient convertToPatient(PatientDto patientDto) {
    return Patient.builder().family(patientDto.getFamily()).given(patientDto.getGiven()).birthdate(patientDto.getDob())
        .sex(patientDto.getSex())
        .address(patientDto.getAddress()).phone(patientDto.getPhone()).build();
  }
}
