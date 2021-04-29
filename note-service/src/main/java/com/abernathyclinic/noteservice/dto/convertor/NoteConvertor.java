package com.abernathyclinic.noteservice.dto.convertor;

import com.abernathyclinic.noteservice.dto.NoteDto;
import com.abernathyclinic.noteservice.model.Note;

public class NoteConvertor {

  public NoteDto convertToNoteDto(Note note) {
    return NoteDto.builder().id(note.getId()).patId(note.getPatId()).patient(note.getPatient()).recommendations(
        note.getRecommendations()).build();
  }

  public Note convertToNote(NoteDto noteDto) {
    return Note.builder().patId(noteDto.getPatId()).patient(noteDto.getPatient()).recommendations(
        noteDto.getRecommendations()).build();
  }
}
