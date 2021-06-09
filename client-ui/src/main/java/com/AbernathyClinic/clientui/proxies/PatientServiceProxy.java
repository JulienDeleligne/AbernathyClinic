package com.abernathyclinic.clientui.proxies;

import com.abernathyclinic.clientui.beans.PatientBean;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "patient-service", url = "patient-service:8081")
public interface PatientServiceProxy {

  @GetMapping(value = "/patient")
  List<PatientBean> patientList();

  @GetMapping(value = "/patient/{id}")
  PatientBean findPatientById(@PathVariable("id") Integer id);

  @PostMapping(value = "/patient/add")
  PatientBean save(@RequestBody PatientBean patient);

  @PutMapping(value = "/patient/update/{id}")
  PatientBean update(@RequestBody PatientBean patient, @PathVariable("id") Integer id);

  @DeleteMapping(value = "/patient/delete/{id}")
  void delete(@PathVariable("id") Integer id);
}
