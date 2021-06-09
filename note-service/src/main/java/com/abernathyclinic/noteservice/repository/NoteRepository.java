package com.abernathyclinic.noteservice.repository;

import com.abernathyclinic.noteservice.model.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

  Optional<Note> findById(String id);

  List<Note> findAllByPatId(Integer id);

  void deleteById(String id);

  List<Note> findAllByPatient(String patient);
}
