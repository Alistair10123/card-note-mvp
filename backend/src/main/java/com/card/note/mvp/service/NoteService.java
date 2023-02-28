package com.card.note.mvp.service;

import java.util.Optional;

import com.card.note.mvp.entity.NoteDetail;

import reactor.core.publisher.Mono;

public interface NoteService {

    Optional<NoteDetail> getNoteDetail(Long noteId);

    NoteDetail saveNoteDtail(NoteDetail noteDetail);

    void deleteNote(Long id);

}