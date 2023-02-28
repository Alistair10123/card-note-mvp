package com.card.note.mvp.mapper;

import org.mapstruct.Mapper;

import com.card.note.mvp.dto.NoteDTO;
import com.card.note.mvp.entity.NoteDetail;

@Mapper(componentModel = "spring")
public interface NoteDetailMapper {
    

    NoteDTO toDTO(NoteDetail noteDetail);
    
    NoteDetail toEntity(NoteDTO dto);

}
