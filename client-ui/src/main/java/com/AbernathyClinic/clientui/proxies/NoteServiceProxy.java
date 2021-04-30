package com.abernathyclinic.clientui.proxies;

import com.abernathyclinic.clientui.beans.NoteBean;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "note-service", url = "localhost:8082")
public interface NoteServiceProxy {

  @GetMapping(value = "/note/{id}")
  NoteBean findNoteById(@PathVariable String id);

  @GetMapping(value = "/notes/patId/{patId}")
  List<NoteBean> findNotesByPatId(@PathVariable Integer patId);

  @GetMapping(value = "/notes/patient/{patient}")
  List<NoteBean> findNotesByPatient(@PathVariable String patient);

  @PostMapping(value = "/note/add")
  NoteBean save(@RequestBody NoteBean note);

  @PutMapping(value = "/note/update/{id}")
  NoteBean update(@RequestBody NoteBean note, @PathVariable String id);

  @DeleteMapping(value = "/note/delete/{id}")
  void delete(@PathVariable String id);
}
