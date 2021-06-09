package com.abernathyclinic.noteservice.service;

import com.abernathyclinic.noteservice.model.Note;
import com.abernathyclinic.noteservice.repository.NoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class NoteService {

  @Autowired
  private NoteRepository noteRepository;

  public Note findNoteById(String id) {
    Assert.notNull(id, "id must not be null");
    return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Note Id:" + id));
  }

  public List<Note> findNotesByPatId(Integer patId) {
    Assert.notNull(patId, "id must not be null");
    return noteRepository.findAllByPatId(patId);
  }

  public List<Note> findNotesByPatient(String patient) {
    Assert.notNull(patient, "Patient must not be null");
    return noteRepository.findAllByPatient(patient);
  }

  public Note save(Note note) {
    Assert.notNull(note, "Note must not be null");
    return noteRepository.save(note);
  }

  public Note update(Note noteToUpdate, String id) {
    Assert.notNull(noteToUpdate, "Note must not be null");
    var note = findNoteById(id);
    note.setPatId(noteToUpdate.getPatId());
    note.setPatient(noteToUpdate.getPatient());
    note.setRecommendations(noteToUpdate.getRecommendations());
    return save(note);
  }

  public void deleteById(String id) {
    Assert.notNull(id, "id must not be null");
    noteRepository.deleteById(findNoteById(id).getId());
  }
}
