package com.AbernathyClinic.patientservice.service;

import com.AbernathyClinic.patientservice.model.Patient;
import com.AbernathyClinic.patientservice.repository.PatientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

  public List<Patient> getAllPatient() {
    return patientRepository.findAll();
  }

  public Patient findPatientById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Patient Id:" + id));
  }

  public Patient save(Patient patient) {
    Assert.notNull(patient, "Patient must not be null");
    return patientRepository.save(patient);
  }

  public Patient update(Patient patientToUpdate, Integer id) {
    Assert.notNull(patientToUpdate, "Patient must not be null");
    Patient patient = findPatientById(id);
    patient.setFamily(patientToUpdate.getFamily());
    patient.setGiven(patientToUpdate.getGiven());
    patient.setBirthdate(patientToUpdate.getBirthdate());
    patient.setSex(patientToUpdate.getSex());
    patient.setAddress(patientToUpdate.getAddress());
    patient.setPhone(patientToUpdate.getPhone());
    return save(patient);
  }

  public void deleteById(Integer id) {
    Assert.notNull(id, "id must not be null");
    patientRepository.deleteById(findPatientById(id).getId());
  }
}
