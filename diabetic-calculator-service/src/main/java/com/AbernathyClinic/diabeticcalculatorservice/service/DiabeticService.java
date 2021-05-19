package com.AbernathyClinic.diabeticcalculatorservice.service;

import com.AbernathyClinic.diabeticcalculatorservice.bean.NoteBean;
import com.AbernathyClinic.diabeticcalculatorservice.model.Patient;
import com.AbernathyClinic.diabeticcalculatorservice.proxies.NoteServiceProxy;
import com.AbernathyClinic.diabeticcalculatorservice.proxies.PatientServiceProxy;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiabeticService {

  private final List<String> words = List
      .of("a1c", "microalbumine", "taille", "poids", "fumeur", "anormal", "cholestérol",
          "vertige", "rechute", "réaction", "anticorps");
  @Autowired
  private PatientServiceProxy patientServiceProxy;
  @Autowired
  private NoteServiceProxy noteServiceProxy;

  public static int calculateAge(LocalDate birthDate) {
    if (birthDate != null) {
      return Period.between(birthDate, LocalDate.now()).getYears();
    }
    return 0;
  }

  public Patient assess(Integer patId) {
    var patientBean = patientServiceProxy.findPatientById(patId);
    var patient = Patient.builder().family(patientBean.getFamily()).given(patientBean.getGiven())
        .age(calculateAge(patientBean.getDob())).sex(patientBean.getSex()).build();

    List<NoteBean> noteBeans = noteServiceProxy.findNotesByPatId(patId);

    calculateRisk(patient, noteBeans);

    return patient;
  }

  private void calculateRisk(Patient patient, List<NoteBean> noteBeans) {
    String assess = null;
    var result = noteBeans.stream().map(noteBean -> findOccurrenceForOneNote(noteBean.getRecommendations()))
        .mapToInt(Integer::intValue).sum();
    if (result == 0) {
      assess = "None";
    } else if (patient.getAge() >= 30 && result >= 8) {
      assess = "Early onset";
    } else if (patient.getAge() >= 30 && result >= 6) {
      assess = "In Danger";
    } else if (patient.getAge() >= 30 && result >= 2) {
      assess = "Borderline";
    } else if (patient.getSex().equals("M") && patient.getAge() < 30 && result >= 5
        || patient.getSex().equals("F") && patient.getAge() < 30 && result >= 7) {
      assess = "Early onset";
    } else if (patient.getSex().equals("M") && patient.getAge() < 30 && result >= 3
        || patient.getSex().equals("F") && patient.getAge() < 30 && result >= 4) {
      assess = "Danger";
    }
    patient.setResult("diabetes assessment is: " + assess);
  }

  private Integer findOccurrenceForOneNote(String sentence) {

    var wordCount = words.stream().collect(Collectors.toMap(w -> w, w -> 0));
    for (var word : sentence.split("[\\n\\s]")) {
      var lower = word.toLowerCase();
      if (wordCount.containsKey(lower)) {
        wordCount.put(lower, wordCount.get(lower) + 1);
      }
    }
    return wordCount.values().stream().mapToInt(Integer::valueOf).sum();
  }
}
