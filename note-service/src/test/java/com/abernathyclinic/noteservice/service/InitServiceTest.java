package com.abernathyclinic.noteservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.abernathyclinic.noteservice.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InitServiceTest {

  @InjectMocks
  InitService initService;
  @Mock
  NoteRepository noteRepository;

  @Test
  void initTest() {
    //ACT
    initService.init();
    //ASSERT
    verify(noteRepository, times(1)).deleteAll();
    verify(noteRepository, times(29)).save(any());
  }
}
