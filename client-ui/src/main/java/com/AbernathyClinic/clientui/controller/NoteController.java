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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private NoteServiceProxy noteServiceProxy;

  @RequestMapping(value = "/note/list/patId/{patId}")
  public String listNotePatIdPathVariable(@PathVariable("patId") Integer patId, Model model) {
    log.info("Request to note-service");
    var notes = noteServiceProxy.findNotesByPatId(patId);
    model.addAttribute("notes", notes);
    return "note/list";
  }

  @RequestMapping(value = "/note/list/patId", params = "patId")
  public String listNotePatId(@RequestParam Integer patId, Model model) {
    log.info("Request to note-service");
    var notes = noteServiceProxy.findNotesByPatId(patId);
    model.addAttribute("notes", notes);
    return "note/list";
  }

  @RequestMapping(value = "/note/list/patient", params = "patient")
  public String listNotePatient(@RequestParam String patient, Model model) {
    model.addAttribute("notes", noteServiceProxy.findNotesByPatient(patient));
    return "note/list";
  }

  @GetMapping("/note/add/{patId}/{patient}")
  public String addNoteForm(@PathVariable("patId") Integer patId, @PathVariable("patient") String patient,
      NoteBean note, Model model) {
    model.addAttribute("patId", patId);
    model.addAttribute("patient", patient);
    return "note/add";
  }

  @PostMapping("/note/validate/{patId}")
  public String validate(@PathVariable("patId") Integer patId, @Valid NoteBean note, BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "note/add";
    }
    noteServiceProxy.save(note);
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(patId));
    return "redirect:/note/list/patId/" + patId;
  }

  @GetMapping("/note/update/{id}")
  public String showUpdateForm(@PathVariable("id") String id, Model model) {
    model.addAttribute("note", noteServiceProxy.findNoteById(id));
    return "note/update";
  }

  @PostMapping("/note/update/{id}")
  public String updateBid(@PathVariable("id") String id, @Valid NoteBean note,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "note/update";
    }
    noteServiceProxy.update(note, id);
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(note.getPatId()));
    return "redirect:/note/list/patId/" + note.getPatId();
  }

  @GetMapping("/note/delete/{id}/{patId}")
  public String deleteBid(@PathVariable("id") String id, @PathVariable("patId") Integer patId, Model model) {
    noteServiceProxy.delete(id);
    model.addAttribute("notes", noteServiceProxy.findNotesByPatId(patId));
    return "redirect:/note/list/patId/" + patId;
  }
}
