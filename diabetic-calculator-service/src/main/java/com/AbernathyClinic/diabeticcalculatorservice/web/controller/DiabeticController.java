package com.AbernathyClinic.diabeticcalculatorservice.web.controller;

import com.AbernathyClinic.diabeticcalculatorservice.model.Patient;
import com.AbernathyClinic.diabeticcalculatorservice.service.DiabeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiabeticController {

  @Autowired
  private DiabeticService diabeticService;

  @PostMapping(value = "/assess/id")
  public Patient assess(@RequestParam int patId) {
    return diabeticService.assess(patId);
  }
}
