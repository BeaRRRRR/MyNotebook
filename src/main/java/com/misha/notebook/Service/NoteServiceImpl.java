package com.misha.notebook.Service;

import com.misha.notebook.Entity.Note;
import com.misha.notebook.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void addNote(Note note){
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAllOrOrderByDateAsc() {
        return noteRepository.findAllByOrderByDateAsc();
    }

    @Override
    public void updateNote(Note note){
        noteRepository.save(note);
    }

    @Override
    public Note getNoteById(Long id){
        return noteRepository.getOne(id);
    }


}
