package com.card.note.mvp.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.card.note.mvp.entity.NoteDetail;
import com.card.note.mvp.repository.NoteDetailRepository;
import com.card.note.mvp.repository.UserRepository;
import com.card.note.mvp.service.NoteService;

import reactor.core.publisher.Mono;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteDetailRepository noteDetailRepository;

    @Override
    @Transactional
    public Optional<NoteDetail> getNoteDetail(Long noteId) {
        Optional<NoteDetail> note = noteDetailRepository.findById(noteId);
        note.ifPresent(n->n.getOwnerUser().getName());
        return note;
    }

    @Override
    @Transactional
    public NoteDetail saveNoteDtail(NoteDetail noteDetail) {
        userRepository.findById(1L).ifPresent(u -> noteDetail.setOwnerUser(u));
        return noteDetailRepository.save(noteDetail);
    }

    @Override
    @Transactional
    public void deleteNote(Long id) {
        // TODO 軟刪除
        noteDetailRepository.deleteById(id);
    }

}
