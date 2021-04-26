package com.AbernathyClinic.clientui.proxies;

import com.AbernathyClinic.clientui.beans.PatientBean;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "patient-service", url = "localhost:9001")
public interface PatientServiceProxy {

  @GetMapping(value = "/patients")
  List<PatientBean> patientList();

}
