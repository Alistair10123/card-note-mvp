package com.card.note.mvp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.card.note.mvp.entity.NoteDetail;
import com.card.note.mvp.entity.User;


@SpringBootTest

public class NoteDetailRepositoryTest {
    
    @Autowired
    private NoteDetailRepository noteDetailRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testCrudOperations() {
        // create
        NoteDetail noteDetail = new NoteDetail();
        noteDetail.setTitle("Test NoteDetail Title");
        noteDetail.setContent("Test NoteDetail Content");
        User user = new User();
        user.setName("testMan");
        userRepository.save(user);
        noteDetail.setOwnerUser(user);
        assertNull(noteDetail.getId());
  
        noteDetail = noteDetailRepository.save(noteDetail);
        
        assertNotNull(noteDetail.getId());
    
        // read
        Optional<NoteDetail> foundNoteDetail = noteDetailRepository.findById(noteDetail.getId());
        assertTrue(foundNoteDetail.isPresent());
        
        // update
        NoteDetail updatedNoteDetail = foundNoteDetail.get();
        updatedNoteDetail.setTitle("Updated Test NoteDetail Title");
        updatedNoteDetail = noteDetailRepository.save(updatedNoteDetail);
        assertEquals("Updated Test NoteDetail Title", updatedNoteDetail.getTitle());
        
        // delete
        noteDetailRepository.deleteById(updatedNoteDetail.getId());
        assertFalse(noteDetailRepository.findById(updatedNoteDetail.getId()).isPresent());
    }
}

