package com.AbernathyClinic.patientservice.web.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthIndicator {

  @Override
  public Health health() {

    return Health.up().build();
  }
}
