package com.AbernathyClinic.patientservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

  @GetMapping(value = "/patients")
  public String patientsList() {
    return "ok";
  }
}
