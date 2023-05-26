package com.card.note.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.note.mvp.dto.NoteDTO;
import com.card.note.mvp.entity.NoteDetail;
import com.card.note.mvp.service.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notes")
@Api(tags = "card-note api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a note by id", notes = "Returns a note with the given id")
    public NoteDTO getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteDetail(id).get();
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new note", notes = "Creates a new note with the given details")
    public Mono<NoteDetail> createNoteDetail(@RequestBody NoteDTO noteDTO) {
        return noteService.saveNoteDtail(noteDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing note", notes = "Updates an existing note with the given details")
    public Mono<NoteDTO> updateNoteDetail(@PathVariable(value = "id") Long id,
            @RequestBody NoteDTO noteDTO) {
       
        return Mono.just(noteService.updateNoteDtail(id,noteDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a note by id", notes = "Deletes a note with the given id")
    public void deleteNoteDetail(@PathVariable(value = "id") Long id) {
        noteService.deleteNote(id);
    }
}
