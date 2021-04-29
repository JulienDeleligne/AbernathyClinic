package com.abernathyclinic.clientui.controller;

import com.abernathyclinic.clientui.beans.NoteBean;
import com.abernathyclinic.clientui.proxies.NoteServiceProxy;
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
public class NoteController {

  final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private NoteServiceProxy noteServiceProxy;

  @RequestMapping("/note/list/patId/{patId}")
  public String listNotePatId(@PathVariable("patId") Integer patId, Model model) {
    log.info("Request to note-service");
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(patId));
    return "note/list";
  }

  @RequestMapping("/note/list/patient/{patient}")
  public String listNotePatient(@PathVariable String patient, Model model) {
    model.addAttribute("notes", noteServiceProxy.findNotesByPatient(patient));
    return "note/list";
  }

  @GetMapping("/note/add/{patId}")
  public String addNoteForm(@PathVariable Integer patId, NoteBean note, Model model) {
    model.addAttribute("patId", patId);
    return "note/add";
  }

  @PostMapping("/note/validate/{patId}")
  public String validate(@PathVariable Integer patId, @Valid NoteBean note, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "note/add";
    }
    noteServiceProxy.save(note);
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(patId));
    return "redirect:/note/list";
  }

  @GetMapping("/note/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("note", noteServiceProxy.findNoteById(id));
    return "note/update";
  }

  @PostMapping("/note/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid NoteBean note,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "note/update";
    }
    noteServiceProxy.update(note, id);
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(note.getPatId()));
    return "redirect:/note/list";
  }

  @GetMapping("/note/delete/{id}/{patId}")
  public String deleteBid(@PathVariable("id") Integer id, @PathVariable("patId") Integer patId, Model model) {
    noteServiceProxy.delete(id);
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(patId));
    return "redirect:/note/list";
  }
}
