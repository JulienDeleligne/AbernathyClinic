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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PatientServiceTest {

  @InjectMocks
  PatientService patientService;
  @Mock
  PatientRepository patientRepository;

  List<Patient> patientList = List
      .of(Patient.builder().id(1).family("John").given("Smith").birthdate(LocalDate.of(2000, 10, 31)).sex("M")
              .address("home").phone("111").build(),
          Patient.builder().id(1).family("Jenna").given("Gordon").birthdate(LocalDate.of(2000, 10, 31)).sex("F")
              .address("work").phone("222").build());
  Patient patient = Patient.builder().id(1).family("John").given("Smith").birthdate(LocalDate.of(2000, 10, 31)).sex("M")
      .address("home").phone("111").build();

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void patientListTest() {
    //ARRANGE
    when(patientRepository.findAll()).thenReturn(patientList);
    //ASSERT
    assertThat(patientService.getAllPatient()).isEqualTo(patientList);
  }

  @Test
  public void findPatientByIdTest() {
    //ARRANGE
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
    //ASSERT
    assertThat(patientService.findPatientById(1)).isEqualTo(patient);
  }

  @Test
  public void saveTest() {
    //ACT
    patientService.save(patient);
    //ASSERT
    verify(patientRepository, times(1)).save(any());
  }

  @Test
  public void savesTest() {
    //ACT
    patientService.saves(patientList);
    //ASSERT
    verify(patientRepository, times(1)).saveAll(any());
  }

  @Test
  public void updateTest() {
    //ARRANGE
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
    //ACT
    patientService.update(patient, 1);
    //ASSERT
    verify(patientRepository, times(1)).save(patient);
  }

  @Test
  public void deleteTest() {
    //ARRANGE
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
    //ACT
    patientService.deleteById(1);
    //ASSERT
    verify(patientRepository, times(1)).deleteById(1);
  }
}
