package com.abernathyclinic.noteservice.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abernathyclinic.noteservice.model.Note;
import com.abernathyclinic.noteservice.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

  final Note note = Note.builder().id("1").patId(1).patient("Smith").recommendations("nada").build();
  final List<Note> noteList = List.of(Note.builder().id("1").patId(1).patient("Smith").recommendations("nada").build(),
      Note.builder().id("2").patId(1).patient("Smith").recommendations("a lot").build());
  @InjectMocks
  NoteService noteService;
  @Mock
  NoteRepository noteRepository;

  @Test
  void findNoteByIdTest() {
    //ARRANGE
    when(noteRepository.findById("1")).thenReturn(Optional.of(note));
    //ASSERT
    assertThat(noteService.findNoteById("1")).isEqualTo(note);
  }

  @Test
  void findNoteByPatIdTest() {
    //ARRANGE
    when(noteRepository.findAllByPatId(1)).thenReturn(noteList);
    //ASSERT
    assertThat(noteService.findNotesByPatId(1)).isEqualTo(noteList);
  }

  @Test
  void findNotesByPatientTest() {
    //ARRANGE
    when(noteRepository.findAllByPatient("Smith")).thenReturn(noteList);
    //ASSERT
    assertThat(noteService.findNotesByPatient("Smith")).isEqualTo(noteList);
  }

  @Test
  void saveTest() {
    //ACT
    noteService.save(note);
    //ASSERT
    verify(noteRepository, times(1)).save(any());
  }

  @Test
  void updateTest() {
    //ARRANGE
    when(noteRepository.findById("1")).thenReturn(Optional.of(note));
    //ACT
    noteService.update(note, "1");
    //ASSERT
    verify(noteRepository, times(1)).save(note);
  }

  @Test
  void deleteTest() {
    //ARRANGE
    when(noteRepository.findById("1")).thenReturn(Optional.of(note));
    //ACT
    noteService.deleteById("1");
    //ASSERT
    verify(noteRepository, times(1)).deleteById("1");
  }
}
