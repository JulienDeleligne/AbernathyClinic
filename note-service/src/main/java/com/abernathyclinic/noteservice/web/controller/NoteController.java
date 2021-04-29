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

  @GetMapping(value = "/notes")
  public List<NoteDto> notesList() {
    return noteService.getAllNote().stream().map(noteConvertor::convertToNoteDto).collect(
        Collectors.toList());
  }

  @GetMapping(value = "/note/{id}")
  public NoteDto findNoteById(@PathVariable Integer id) {
    return noteConvertor.convertToNoteDto(noteService.findNoteById(id));
  }

  /*
  @GetMapping(value = "/note/{patient}")
  public NoteDto findNoteByPatient(@PathVariable String patient) {
    return noteConvertor.convertToNoteDto(noteService.findNoteByPatient(patient));
  }*/

  @PostMapping(value = "/add")
  public NoteDto save(@Valid @RequestBody NoteDto note) {
    return noteConvertor.convertToNoteDto(noteService.save(noteConvertor.convertToNote(note)));
  }

  @PutMapping(value = "/update/{id}")
  public NoteDto update(@Valid @RequestBody NoteDto note, @PathVariable Integer id) {
    return noteConvertor.convertToNoteDto(noteService.update(noteConvertor.convertToNote(note), id));
  }

  @DeleteMapping(value = "/delete/{id}")
  public void delete(@PathVariable Integer id) {
    noteService.deleteById(id);
  }
}
