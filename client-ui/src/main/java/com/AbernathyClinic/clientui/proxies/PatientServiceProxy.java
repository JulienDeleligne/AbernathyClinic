package com.AbernathyClinic.clientui.proxies;

import com.AbernathyClinic.clientui.beans.PatientBean;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "patient-service", url = "localhost:8081")
public interface PatientServiceProxy {

  @GetMapping(value = "/patients")
  List<PatientBean> patientList();

  @GetMapping(value = "/patient/{id}")
  PatientBean findPatientById(@PathVariable Integer id);

  @PostMapping(value = "/add")
  PatientBean save(@RequestBody PatientBean patient);

  @PutMapping(value = "/update/{id}")
  PatientBean update(@RequestBody PatientBean patient, @PathVariable Integer id);

  @DeleteMapping(value = "/delete/{id}")
  void delete(@PathVariable Integer id);
}
