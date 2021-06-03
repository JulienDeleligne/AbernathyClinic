package com.abernathyclinic.noteservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abernathyclinic.noteservice.dto.NoteDto;
import com.abernathyclinic.noteservice.model.Note;
import com.abernathyclinic.noteservice.service.NoteService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

  @InjectMocks
  NoteController noteController;
  @Mock
  NoteService noteService;

  NoteDto noteDto = NoteDto.builder().id("1").patId(1).patient("Smith").recommendations("nada").build();
  Note note = Note.builder().id("1").patId(1).patient("Smith").recommendations("nada").build();
  List<Note> noteList = List.of(Note.builder().id("1").patId(1).patient("Smith").recommendations("nada").build(),
      Note.builder().id("2").patId(1).patient("Smith").recommendations("a lot").build());

  @Test
  void findNoteByIdTest() {
    //ARRANGE
    when(noteService.findNoteById("1")).thenReturn(note);
    //ACT
    noteController.findNoteById("1");
    //ASSERT
    verify(noteService, times(1)).findNoteById("1");
  }

  @Test
  void findNoteByPatIdTest() {
    //ARRANGE
    when(noteService.findNotesByPatId(1)).thenReturn(noteList);
    //ACT
    noteController.findNotesByPatId(1);
    //ASSERT
    verify(noteService, times(1)).findNotesByPatId(1);
  }

  @Test
  void findNotesByPatientTest() {
    //ARRANGE
    when(noteService.findNotesByPatient("Smith")).thenReturn(noteList);
    //ACT
    noteController.findNotesByPatient("Smith");
    //ASSERT
    verify(noteService, times(1)).findNotesByPatient("Smith");
  }

  @Test
  void saveTest() {
    //ARRANGE
    when(noteService.save(note)).thenReturn(note);
    //ACT
    noteController.save(noteDto);
    //ASSERT
    verify(noteService, times(1)).save(note);
  }

  @Test
  void updateTest() {
    //ARRANGE
    when(noteService.update(any(), any())).thenReturn(note);
    //ACT
    noteController.update(noteDto, "1");
    //ASSERT
    verify(noteService, times(1)).update(note, "1");
  }

  @Test
  void deleteTest() {
    //ACT
    noteController.delete("1");
    //ASSERT
    verify(noteService, times(1)).deleteById("1");
  }
}
