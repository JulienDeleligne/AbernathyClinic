package com.abernathyclinic.patientservice.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abernathyclinic.patientservice.model.Patient;
import com.abernathyclinic.patientservice.repository.PatientRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

  final List<Patient> patientList = List
      .of(Patient.builder().id(1).family("John").given("Smith").birthdate(LocalDate.of(2000, 10, 31)).sex("M")
              .address("home").phone("111").build(),
          Patient.builder().id(1).family("Jenna").given("Gordon").birthdate(LocalDate.of(2000, 10, 31)).sex("F")
              .address("work").phone("222").build());
  final Patient patient = Patient.builder().id(1).family("John").given("Smith").birthdate(LocalDate.of(2000, 10, 31))
      .sex("M")
      .address("home").phone("111").build();
  @InjectMocks
  PatientService patientService;
  @Mock
  PatientRepository patientRepository;

  @Test
  void patientListTest() {
    //ARRANGE
    when(patientRepository.findAll()).thenReturn(patientList);
    //ASSERT
    assertThat(patientService.getAllPatient()).isEqualTo(patientList);
  }

  @Test
  void findPatientByIdTest() {
    //ARRANGE
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
    //ASSERT
    assertThat(patientService.findPatientById(1)).isEqualTo(patient);
  }

  @Test
  void saveTest() {
    //ACT
    patientService.save(patient);
    //ASSERT
    verify(patientRepository, times(1)).save(any());
  }

  @Test
  void savesTest() {
    //ACT
    patientService.saves(patientList);
    //ASSERT
    verify(patientRepository, times(1)).saveAll(any());
  }

  @Test
  void updateTest() {
    //ARRANGE
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
    //ACT
    patientService.update(patient, 1);
    //ASSERT
    verify(patientRepository, times(1)).save(patient);
  }

  @Test
  void deleteTest() {
    //ARRANGE
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
    //ACT
    patientService.deleteById(1);
    //ASSERT
    verify(patientRepository, times(1)).deleteById(1);
  }
}
