package com.abernathyclinic.noteservice.service;

import com.abernathyclinic.noteservice.model.Note;
import com.abernathyclinic.noteservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {

  @Autowired
  private NoteRepository noteRepository;

  public void init() {
    noteRepository.deleteAll();
    noteRepository.save(
        Note.builder().patId(1).patient("Ferguson").recommendations("Le patient déclare qu'il « se sent très bien »\n"
            + "Poids égal ou inférieur au poids recommandé").build());
    noteRepository.save(Note.builder().patId(1).patient("Ferguson")
        .recommendations("Le patient déclare qu'il se sent fatigué pendant la journée\n"
            + "Il se plaint également de douleurs musculaires\n"
            + "Tests de laboratoire indiquant une microalbumine élevée").build());
    noteRepository.save(Note.builder().patId(1).patient("Ferguson")
        .recommendations("Le patient déclare qu'il ne se sent pas si fatigué que ça\n"
            + "Fumeur, il a arrêté dans les 12 mois précédents\n"
            + "Tests de laboratoire indiquant que les anticorps sont élevés").build());
    noteRepository.save(Note.builder().patId(2).patient("Rees")
        .recommendations("Le patient déclare qu'il ressent beaucoup de stress au travail\n"
            + "Il se plaint également que son audition est anormale dernièrement").build());
    noteRepository.save(Note.builder().patId(2).patient("Rees")
        .recommendations("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois\n"
            + "Il remarque également que son audition continue d'être anormale").build());
    noteRepository.save(Note.builder().patId(2).patient("Rees")
        .recommendations("Tests de laboratoire indiquant une microalbumine élevée").build());
    noteRepository.save(Note.builder().patId(2).patient("Rees")
        .recommendations("Le patient déclare que tout semble aller bien\n"
            + "Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé\n"
            + "Le patient déclare qu’il fume depuis longtemps").build());
  }
}
