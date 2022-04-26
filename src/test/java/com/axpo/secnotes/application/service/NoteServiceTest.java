package com.axpo.secnotes.application.service;

import com.axpo.secnotes.application.service.exception.NoteNotFoundException;
import com.axpo.secnotes.domain.Note;
import com.axpo.secnotes.infraestructure.entities.DeleteTimeType;
import com.axpo.secnotes.infraestructure.entities.NoteEntity;
import com.axpo.secnotes.infraestructure.mapper.NoteMapper;
import com.axpo.secnotes.infraestructure.repository.NoteRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class NoteServiceTest
{

    private Faker faker = new Faker();

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteMapper noteMapper;

    @InjectMocks
    private NoteService noteService;

    @Test
    void create ()
    {
        Note note = new Note(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), faker.lorem().paragraph(), com.axpo.secnotes.domain.DeleteTimeType.AFTER_READING , LocalDateTime.now());
        NoteEntity noteEntity = new NoteEntity(note.getId(), note.getText(), DeleteTimeType.AFTER_READING, note.getLocalDateTime());
        when(noteRepository.save(any())).thenReturn(noteEntity);
        when(noteMapper.entityToDomain(any())).thenReturn(note);
        when(noteMapper.domainToEntity(any())).thenReturn(noteEntity);
        note = noteService.create(note);
        assertEquals(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), note.getId());
    }

    @Test
    void read () throws NoteNotFoundException
    {
        Note note = new Note(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), faker.lorem().paragraph(), com.axpo.secnotes.domain.DeleteTimeType.AFTER_READING , LocalDateTime.now());
        NoteEntity noteEntity = new NoteEntity(note.getId(), note.getText(), DeleteTimeType.AFTER_READING, note.getLocalDateTime());
        when(noteRepository.findById(any())).thenReturn(Optional.of(noteEntity));
        when(noteMapper.entityToDomain(any())).thenReturn(note);
        Note noteReturned = noteService.read(note);
        assertEquals(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), noteReturned.getId());
    }

    @Test()
    void readEmpty ()
    {
        Note note = new Note(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), faker.lorem().paragraph(), com.axpo.secnotes.domain.DeleteTimeType.AFTER_READING , LocalDateTime.now());
        when(noteRepository.findById(any())).thenReturn(Optional.empty());
        NoteNotFoundException noteNotFoundException = assertThrows(NoteNotFoundException.class, () -> {noteService.read(note);});
        String message = "Note with id [f1ac6c91-23dc-4b12-a534-f62f452c9169] not found";
        assertEquals(message, noteNotFoundException.getMessage());
    }

    @Test
    void delete ()
    {
        Note note = new Note(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), faker.lorem().paragraph(), com.axpo.secnotes.domain.DeleteTimeType.AFTER_READING , LocalDateTime.now());
        NoteEntity noteEntity = new NoteEntity(note.getId(), note.getText(), DeleteTimeType.AFTER_READING, note.getLocalDateTime());
        when(noteRepository.existsById(any())).thenReturn(Boolean.TRUE);
        assertTrue(noteService.delete(note));
    }

    @Test
    void deleteNotFound ()
    {
        Note note = new Note(UUID.fromString("f1ac6c91-23dc-4b12-a534-f62f452c9169"), faker.lorem().paragraph(), com.axpo.secnotes.domain.DeleteTimeType.AFTER_READING , LocalDateTime.now());
        NoteEntity noteEntity = new NoteEntity(note.getId(), note.getText(), DeleteTimeType.AFTER_READING, note.getLocalDateTime());
        when(noteRepository.existsById(any())).thenReturn(Boolean.FALSE);
        NoteNotFoundException noteNotFoundException = assertThrows(NoteNotFoundException.class, () -> {noteService.delete(note);});
        String message = "Note with id [f1ac6c91-23dc-4b12-a534-f62f452c9169] not found";
        assertEquals(message, noteNotFoundException.getMessage());

        assertTrue(true);


    }

}