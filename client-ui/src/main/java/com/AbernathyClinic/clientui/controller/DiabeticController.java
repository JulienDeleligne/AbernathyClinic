package com.abernathyclinic.clientui.controller;

import com.abernathyclinic.clientui.proxies.DiabeticServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiabeticController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private DiabeticServiceProxy diabeticServiceProxy;

  @RequestMapping(value = "/diabetic/patId", params = "patId")
  public String listNotePatId(@RequestParam Integer patId, Model model) {
    log.info("Request to diabetic-service");
    var diabetic = diabeticServiceProxy.assessDiabetic(patId);
    model.addAttribute("diabetic", diabetic);
    return "diabetic/home";
  }
}
