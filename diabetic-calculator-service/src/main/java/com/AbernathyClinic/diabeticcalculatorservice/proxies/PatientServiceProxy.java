package com.AbernathyClinic.diabeticcalculatorservice.proxies;

import com.AbernathyClinic.diabeticcalculatorservice.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "localhost:8081")
public interface PatientServiceProxy {

  @GetMapping(value = "/patient/{id}")
  PatientBean findPatientById(@PathVariable Integer id);
}
