package com.abernathyclinic.clientui.proxies;

import com.abernathyclinic.clientui.beans.DiabeticBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "diabetic-calculator-service", url = "diabetic-service:8080")
public interface DiabeticServiceProxy {

  @PostMapping(value = "/assess/id")
  DiabeticBean assessDiabetic(@RequestParam int patId);
}
