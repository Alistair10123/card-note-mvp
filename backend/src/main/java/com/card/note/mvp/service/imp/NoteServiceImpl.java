package com.card.note.mvp.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.card.note.mvp.dto.NoteDTO;
import com.card.note.mvp.entity.NoteDetail;
import com.card.note.mvp.mapper.NoteDetailMapper;
import com.card.note.mvp.repository.NoteDetailRepository;
import com.card.note.mvp.repository.UserRepository;
import com.card.note.mvp.security.SecurityUtils;
import com.card.note.mvp.service.NoteService;
import com.card.note.mvp.service.UserService;

import reactor.core.publisher.Mono;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteDetailRepository noteDetailRepository;

    @Autowired
    private NoteDetailMapper noteMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Optional<NoteDTO> getNoteDetail(Long noteId) {
        Optional<NoteDetail> note = noteDetailRepository.findById(noteId);
        
        return note.map(n -> noteMapper.toDTO(n));
    }

    @Override
    @Transactional
    public Mono<NoteDetail> saveNoteDtail(NoteDTO noteDTO) {
       return  SecurityUtils.getCurrentUserLogin()
        .flatMap(login -> userService.getUserWithAuthoritiesByLogin(login))
        .flatMap(user -> {
            if(user != null) {
                NoteDetail noteDetail = noteMapper.toEntity(noteDTO);
                noteDetail.setOwnerUser(user);
                return Mono.just(noteDetailRepository.save(noteDetail));
            }else {
                return Mono.error(new RuntimeException("not login user"));
            }
           
        });
        
    }

    @Override
    @Transactional
    public void deleteNote(Long id) {
        // TODO 軟刪除
        noteDetailRepository.deleteById(id);
    }

    @Override
    public NoteDTO updateNoteDtail(Long id, NoteDTO dto) {
        noteDetailRepository.findById(id).ifPresent(note->{
            note.setTitle(dto.getTitle());
            note.setContent(dto.getContent());
            noteDetailRepository.save(note);
        });

        return dto;
    }

}
