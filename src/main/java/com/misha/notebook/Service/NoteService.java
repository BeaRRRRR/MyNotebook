package com.misha.notebook.Service;

import com.misha.notebook.Entity.Note;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NoteService {

    Note getNoteById(Long id);
    void updateNote(Note note);
    void addNote(Note note);
    void deleteNote(Long id);
    List<Note> findAllOrderByDateAsc();
    List<Note> findByDone(boolean done, Sort sort);
    List<Note> findAllOrderByDateDesc();
    List<Note> findAll();
}
