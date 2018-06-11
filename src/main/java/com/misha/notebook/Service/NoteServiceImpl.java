package com.misha.notebook.Service;

import com.misha.notebook.Entity.Note;
import com.misha.notebook.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    private int pageSize,currentPage;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void addNote(Note note){
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findAllOrderByDateAsc() {
        return noteRepository.findAll(new PageRequest(currentPage,pageSize, new Sort(Sort.Direction.ASC,"date"))).getContent();

    }

    @Override
    public void updateNote(Note note){
        noteRepository.save(note);
    }

    @Override
    public Note getNoteById(Long id){
        return noteRepository.findById(id).get();
    }

    @Override
    public List<Note> findByDone(boolean done,Sort sort) {
        if (done){
            return noteRepository.findByDoneIsTrue(new PageRequest(currentPage,pageSize,sort));
        }
        return noteRepository.findByDoneIsFalse(new PageRequest(currentPage,pageSize,sort));
    }

    @Override
    public List<Note> findAllOrderByDateDesc() {
        return noteRepository.findAll(new PageRequest(currentPage,pageSize, new Sort(Sort.Direction.DESC,"date"))).getContent();
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
