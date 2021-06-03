package com.abernathyclinic.patientservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.abernathyclinic.patientservice.dto.InitDto;
import com.abernathyclinic.patientservice.dto.PatientDto;
import com.abernathyclinic.patientservice.repository.PatientRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InitServiceTest {

  @InjectMocks
  InitService initService;
  @Mock
  PatientService patientService;

  List<PatientDto> patientList = List
      .of(PatientDto.builder().id(1).family("John").given("Smith").dob("2000-10-31").sex("M")
              .address("home").phone("111").build(),
          PatientDto.builder().id(1).family("Jenna").given("Gordon").dob("1990-10-31").sex("F")
              .address("work").phone("222").build());

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void initTest() {
    //ACT
    initService.init(InitDto.builder().patients(patientList).build());
    //ASSERT
    verify(patientService, times(1)).saves(any());

  }
}
