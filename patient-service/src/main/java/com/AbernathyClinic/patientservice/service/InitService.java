package com.abernathyclinic.patientservice.service;

import static java.util.stream.Collectors.toList;

import com.abernathyclinic.patientservice.dto.InitDto;
import com.abernathyclinic.patientservice.dto.convertor.PatientConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {

  @Autowired
  private PatientService patientService;

  public void init(InitDto initDto) {
    patientService.saves(initDto.getPatients().stream().map(PatientConvertor::convertToPatient).collect(toList()));
  }
}
