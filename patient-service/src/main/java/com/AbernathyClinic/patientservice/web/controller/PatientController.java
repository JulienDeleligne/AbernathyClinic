package com.abernathyclinic.patientservice.web.controller;

import com.abernathyclinic.patientservice.dto.PatientDto;
import com.abernathyclinic.patientservice.dto.convertor.PatientConvertor;
import com.abernathyclinic.patientservice.service.PatientService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

  private final PatientConvertor patientConvertor = new PatientConvertor();
  @Autowired
  private PatientService patientService;

  @GetMapping(value = "/patient")
  public List<PatientDto> patientList() {
    return patientService.getAllPatient().stream().map(patientConvertor::convertToPatientDto).collect(
        Collectors.toList());
  }

  @GetMapping(value = "/patient/{id}")
  public PatientDto findPatientById(@PathVariable Integer id) {
    return patientConvertor.convertToPatientDto(patientService.findPatientById(id));
  }

  @PostMapping(value = "/patient/add")
  public PatientDto save(@Valid @RequestBody PatientDto patient) {
    return patientConvertor.convertToPatientDto(patientService.save(patientConvertor.convertToPatient(patient)));
  }

  @PutMapping(value = "/patient/update/{id}")
  public PatientDto update(@Valid @RequestBody PatientDto patient, @PathVariable Integer id) {
    return patientConvertor.convertToPatientDto(patientService.update(patientConvertor.convertToPatient(patient), id));
  }

  @DeleteMapping(value = "/patient/delete/{id}")
  public void delete(@PathVariable Integer id) {
    patientService.deleteById(id);
  }
}
