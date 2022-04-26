package com.axpo.secnotes.infraestructure.mapper;

import com.axpo.secnotes.domain.Note;
import com.axpo.secnotes.infraestructure.entities.DeleteTimeType;
import com.axpo.secnotes.infraestructure.entities.NoteEntity;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteMapperTest
{

    private final NoteMapper noteMapper = new NoteMapper();
    private final Faker faker = new Faker();

    @Test
    void entityToDomain ()
    {
        NoteEntity noteEntity = new NoteEntity(UUID.randomUUID(), faker.lorem().paragraph(), DeleteTimeType.AFTER_READING , LocalDateTime.now());
        Note note = noteMapper.entityToDomain(noteEntity);
        assertEquals(note.getId(), noteEntity.getId());
    }


    @Test
    void domainToEntity ()
    {
        Note note = new Note(UUID.randomUUID(), faker.lorem().paragraph(), com.axpo.secnotes.domain.DeleteTimeType.AFTER_READING , LocalDateTime.now());
        NoteEntity noteEntity = noteMapper.domainToEntity(note);
        assertEquals(note.getId(), noteEntity.getId());
    }

    @Test
    void domainToEntityWithEmptyFields ()
    {
        Note note = new Note(faker.lorem().paragraph());
        NoteEntity noteEntity = noteMapper.domainToEntity(note);
        assertEquals(note.getText(), noteEntity.getText());
    }
}