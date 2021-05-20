package com.AbernathyClinic.diabeticcalculatorservice.proxies;

import com.AbernathyClinic.diabeticcalculatorservice.bean.NoteBean;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "note-service", url = "note-service:8082")
public interface NoteServiceProxy {

  @GetMapping(value = "/notes/patId/{patId}")
  List<NoteBean> findNotesByPatId(@PathVariable Integer patId);


}
