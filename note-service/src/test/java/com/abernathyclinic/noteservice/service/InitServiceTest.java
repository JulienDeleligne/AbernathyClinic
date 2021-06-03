package com.abernathyclinic.noteservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.abernathyclinic.noteservice.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InitServiceTest {

  @InjectMocks
  InitService initService;
  @Mock
  NoteRepository noteRepository;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void initTest() {
    //ACT
    initService.init();
    //ASSERT
    verify(noteRepository, times(1)).deleteAll();
    verify(noteRepository, times(29)).save(any());
  }
}
