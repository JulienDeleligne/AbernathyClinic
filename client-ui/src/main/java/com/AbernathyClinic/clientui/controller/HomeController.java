package com.abernathyclinic.clientui.controller;

import com.abernathyclinic.clientui.proxies.PatientServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

  final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private PatientServiceProxy patientServiceProxy;

  @RequestMapping("/")
  public String accueil(Model model) {
    return "home";
  }

  @RequestMapping("/home")
  public String home(Model model) {
    return "home";
  }
}
