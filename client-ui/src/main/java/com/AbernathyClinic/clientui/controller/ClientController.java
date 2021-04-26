package com.AbernathyClinic.clientui.controller;

import com.AbernathyClinic.clientui.beans.PatientBean;
import com.AbernathyClinic.clientui.proxies.PatientServiceProxy;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

  @Autowired
  private PatientServiceProxy patientServiceProxy;

  Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/")
  public String accueil(Model model) {
    log.info("Request to patient-service");
    List<PatientBean> patients = patientServiceProxy.patientList();
    model.addAttribute("patients", patients);
    return "Home";
  }
}
