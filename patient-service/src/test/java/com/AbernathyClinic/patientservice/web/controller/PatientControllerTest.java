package com.abernathyclinic.patientservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abernathyclinic.patientservice.dto.PatientDto;
import com.abernathyclinic.patientservice.model.Patient;
import com.abernathyclinic.patientservice.service.PatientService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

  @InjectMocks
  PatientController patientController;
  @Mock
  PatientService patientService;

  PatientDto patientDto = PatientDto.builder().id(1).family("John").given("Smith").dob("2000-10-31").sex("M")
      .address("home").phone("111").build();
  Patient patient = Patient.builder().id(1).family("John").given("Smith").birthdate(LocalDate.of(2000, 10, 31)).sex("M")
      .address("home").phone("111").build();

  @Test
  void patientListTest() {
    //ACT
    patientController.patientList();
    //ASSERT
    verify(patientService, times(1)).getAllPatient();
  }

  @Test
  void findPatientByIdTest() {
    //ARRANGE
    when(patientService.findPatientById(1)).thenReturn(patient);
    //ACT
    patientController.findPatientById(1);
    //ASSERT
    verify(patientService, times(1)).findPatientById(1);
  }

  @Test
  void saveTest() {
    //ARRANGE
    when(patientService.save(patient)).thenReturn(patient);
    //ACT
    patientController.save(patientDto);
    //ASSERT
    verify(patientService, times(1)).save(patient);
  }

  @Test
  void updateTest() {
    //ARRANGE
    when(patientService.findPatientById(1)).thenReturn(patient);
    when(patientService.update(any(), any())).thenReturn(patient);
    //ACT
    patientController.update(patientDto, 1);
    //ASSERT
    verify(patientService, times(1)).update(patient, 1);
  }

  @Test
  void deleteTest() {
    //ACT
    patientController.delete(1);
    //ASSERT
    verify(patientService, times(1)).deleteById(1);
  }
}
