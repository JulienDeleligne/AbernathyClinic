package com.abernathyclinic.noteservice.web.controller;


import com.abernathyclinic.noteservice.dto.NoteDto;
import com.abernathyclinic.noteservice.dto.convertor.NoteConvertor;
import com.abernathyclinic.noteservice.service.NoteService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

  private final NoteConvertor noteConvertor = new NoteConvertor();
  @Autowired
  private NoteService noteService;

  @GetMapping(value = "/note/{id}")
  public NoteDto findNoteById(@PathVariable String id) {
    return noteConvertor.convertToNoteDto(noteService.findNoteById(id));
  }

  @GetMapping(value = "/notes/patId/{patId}")
  public List<NoteDto> findNotesByPatId(@PathVariable Integer patId) {
    return noteService.findNotesByPatId(patId).stream().map(noteConvertor::convertToNoteDto)
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/notes/patient/{patient}")
  public List<NoteDto> findNotesByPatient(@PathVariable String patient) {
    return noteService.findNotesByPatient(patient).stream().map(noteConvertor::convertToNoteDto).collect(
        Collectors.toList());
  }

  @PostMapping(value = "/note/add")
  public NoteDto save(@Valid @RequestBody NoteDto note) {
    return noteConvertor.convertToNoteDto(noteService.save(noteConvertor.convertToNote(note)));
  }

  @PutMapping(value = "/note/update/{id}")
  public NoteDto update(@Valid @RequestBody NoteDto note, @PathVariable String id) {
    return noteConvertor.convertToNoteDto(noteService.update(noteConvertor.convertToNote(note), id));
  }

  @DeleteMapping(value = "/note/delete/{id}")
  public void delete(@PathVariable String id) {
    noteService.deleteById(id);
  }

  // create endpoint init
}
