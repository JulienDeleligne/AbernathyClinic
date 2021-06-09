package com.AbernathyClinic.diabeticcalculatorservice.web.controller;

import com.AbernathyClinic.diabeticcalculatorservice.model.Patient;
import com.AbernathyClinic.diabeticcalculatorservice.service.DiabeticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("API for assessing the patient risk for diabetes ")
public class DiabeticController {

  @Autowired
  private DiabeticService diabeticService;

  @ApiOperation(value = "Asses the patient rik for diabetes using his age, sex and occurrence in the patient's note")
  @PostMapping(value = "/assess/id")
  public Patient assess(@RequestParam int patId) {
    return diabeticService.assess(patId);
  }
}
