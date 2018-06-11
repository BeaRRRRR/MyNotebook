package com.misha.notebook.Repository;

import com.misha.notebook.Entity.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note,Long> {

    List<Note> findByDoneIsFalse(Pageable pageable);
    List<Note> findByDoneIsTrue(Pageable pageable);
    List<Note> findAll();

}
