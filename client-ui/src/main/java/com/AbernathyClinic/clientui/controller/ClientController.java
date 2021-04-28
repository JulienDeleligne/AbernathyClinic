package com.abernathyclinic.clientui.controller;

import com.abernathyclinic.clientui.beans.PatientBean;
import com.abernathyclinic.clientui.proxies.PatientServiceProxy;
import java.time.LocalDate;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

  final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private PatientServiceProxy patientServiceProxy;

  @RequestMapping("/")
  public String accueil(Model model) {
    log.info("Request to patient-service");
    model.addAttribute("patients", patientServiceProxy.patientList());
    model.addAttribute("localDate", LocalDate.now());
    return "list";
  }

  @RequestMapping("/list")
  public String home(Model model) {
    model.addAttribute("patients", patientServiceProxy.patientList());
    model.addAttribute("localDate", LocalDate.now());
    return "list";
  }

  @GetMapping("/add")
  public String addBidForm(PatientBean patient) {
    return "add";
  }

  @PostMapping("/validate")
  public String validate(@Valid PatientBean patient, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "add";
    }
    patientServiceProxy.save(patient);
    model.addAttribute("patients", patientServiceProxy.patientList());
    return "redirect:/list";
  }

  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("patient", patientServiceProxy.findPatientById(id));
    return "update";
  }

  @PostMapping("/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid PatientBean patient,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "update";
    }
    patientServiceProxy.update(patient, id);
    model.addAttribute("patients", patientServiceProxy.patientList());
    return "redirect:/list";
  }

  @GetMapping("/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    patientServiceProxy.delete(id);
    model.addAttribute("patients", patientServiceProxy.patientList());
    return "redirect:/list";
  }
}
