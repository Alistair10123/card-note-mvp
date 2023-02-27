package com.card.note.mvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.card.note.mvp.entity.NoteDetail;


@Repository
public interface NoteDetailRepository extends JpaRepository<NoteDetail, Long> {
    
}
