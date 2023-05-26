package com.card.note.mvp.service;

import java.util.Optional;

import com.card.note.mvp.dto.NoteDTO;
import com.card.note.mvp.entity.NoteDetail;

import reactor.core.publisher.Mono;

public interface NoteService {

    Optional<NoteDTO> getNoteDetail(Long noteId);

    Mono<NoteDetail> saveNoteDtail(NoteDTO noteDetail);

    NoteDTO updateNoteDtail(Long id, NoteDTO noteDetail);

    void deleteNote(Long id);

}