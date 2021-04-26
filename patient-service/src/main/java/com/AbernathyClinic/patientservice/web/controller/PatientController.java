package com.AbernathyClinic.patientservice.web.controller;

import com.AbernathyClinic.patientservice.dto.PatientDto;
import com.AbernathyClinic.patientservice.dto.convertor.PatientConvertor;
import com.AbernathyClinic.patientservice.model.Patient;
import com.AbernathyClinic.patientservice.service.PatientService;
import java.util.List;
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

  @GetMapping(value = "/patients")
  public List<Patient> patientsList() {
    return patientService.getAllPatient();
  }

  @GetMapping(value = "/patient/{id}")
  public PatientDto findPatientById(@PathVariable Integer id) {
    return patientConvertor.convertToPatientDto(patientService.findPatientById(id));
  }

  @PostMapping(value = "/add")
  public PatientDto save(@Valid @RequestBody PatientDto patient) {
    return patientConvertor.convertToPatientDto(patientService.save(patientConvertor.convertToPatient(patient)));
  }

  @PutMapping(value = "/update/{id}")
  public PatientDto update(@Valid @RequestBody PatientDto patient, @PathVariable Integer id) {
    return patientConvertor.convertToPatientDto(patientService.update(patientConvertor.convertToPatient(patient), id));
  }

  @DeleteMapping(value = "/delete/{id}")
  public void delete(@PathVariable Integer id) {
    patientService.deleteById(id);
  }
}
