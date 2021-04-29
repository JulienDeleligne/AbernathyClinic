package com.abernathyclinic.noteservice.repository;

import com.abernathyclinic.noteservice.model.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

  public Optional<Note> findById(String id);

  public List<Note> findAllByPatId(Integer id);

  public void deleteById(String id);

  public List<Note> findAllByPatient(String patient);
}
