package com.AbernathyClinic.diabeticcalculatorservice.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.AbernathyClinic.diabeticcalculatorservice.bean.NoteBean;
import com.AbernathyClinic.diabeticcalculatorservice.bean.PatientBean;
import com.AbernathyClinic.diabeticcalculatorservice.proxies.NoteServiceProxy;
import com.AbernathyClinic.diabeticcalculatorservice.proxies.PatientServiceProxy;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiabeticServiceTest {

  @InjectMocks
  DiabeticService diabeticService;
  @Mock
  PatientServiceProxy patientServiceProxy;
  @Mock
  NoteServiceProxy noteServiceProxy;

  @Test
  void calculateAgeTest() {
    //ASSERT
    assertThat(DiabeticService.calculateAge(LocalDate.now().minusYears(10))).isEqualTo(10);
    assertThat(DiabeticService.calculateAge(LocalDate.now().plusYears(10))).isEqualTo(-10);
    assertThat(DiabeticService.calculateAge(null)).isZero();
  }

  @Test
  void assessNoneTest() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1)).thenReturn(PatientBean.builder().build());
    when(noteServiceProxy.findNotesByPatId(1)).thenReturn(List.of(NoteBean.builder().recommendations("none").build(),
        NoteBean.builder().recommendations("none").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: None");
  }

  @Test
  void assessEarlyOnsetTest() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().dob(LocalDate.now().minusYears(31)).build());
    when(noteServiceProxy.findNotesByPatId(1)).thenReturn(
        List.of(NoteBean.builder().recommendations("microalbumine microalbumine microalbumine microalbumine").build(),
            NoteBean.builder().recommendations("taille taille taille taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: Early onset");
  }

  @Test
  void assessInDangerTest() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().dob(LocalDate.now().minusYears(31)).build());
    when(noteServiceProxy.findNotesByPatId(1))
        .thenReturn(List.of(NoteBean.builder().recommendations("microalbumine microalbumine microalbumine").build(),
            NoteBean.builder().recommendations("taille taille taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: In Danger");
  }

  @Test
  void assessBorderlineTest() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().dob(LocalDate.now().minusYears(31)).build());
    when(noteServiceProxy.findNotesByPatId(1))
        .thenReturn(List.of(NoteBean.builder().recommendations("microalbumine").build(),
            NoteBean.builder().recommendations("taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: Borderline");
  }

  @Test
  void assessEarlyOnsetMaleUnder30Test() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().sex("M").dob(LocalDate.now().minusYears(29)).build());
    when(noteServiceProxy.findNotesByPatId(1)).thenReturn(
        List.of(NoteBean.builder().recommendations("microalbumine").build(),
            NoteBean.builder().recommendations("taille taille taille taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: Early onset");
  }

  @Test
  void assessEarlyOnsetFeMaleUnder30Test() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().sex("F").dob(LocalDate.now().minusYears(29)).build());
    when(noteServiceProxy.findNotesByPatId(1)).thenReturn(
        List.of(NoteBean.builder().recommendations("microalbumine microalbumine microalbumine").build(),
            NoteBean.builder().recommendations("taille taille taille taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: Early onset");
  }

  @Test
  void assessDangerMaleUnder30Test() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().sex("M").dob(LocalDate.now().minusYears(29)).build());
    when(noteServiceProxy.findNotesByPatId(1)).thenReturn(
        List.of(NoteBean.builder().recommendations("microalbumine").build(),
            NoteBean.builder().recommendations("taille taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: Danger");
  }

  @Test
  void assessDangerFeMaleUnder30Test() {
    //ARRANGE
    when(patientServiceProxy.findPatientById(1))
        .thenReturn(PatientBean.builder().sex("F").dob(LocalDate.now().minusYears(29)).build());
    when(noteServiceProxy.findNotesByPatId(1)).thenReturn(
        List.of(NoteBean.builder().recommendations("microalbumine microalbumine microalbumine").build(),
            NoteBean.builder().recommendations("taille").build()));
    //ASSERT
    assertThat(diabeticService.assess(1).getResult()).isEqualTo("diabetes assessment is: Danger");
  }
}
