package com.axpo.secnotes.infraestructure.mapper;

import com.axpo.secnotes.domain.DeleteTimeType;
import com.axpo.secnotes.domain.Note;
import com.axpo.secnotes.infraestructure.entities.NoteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NoteMapper
{
    private final static Logger logger = LoggerFactory.getLogger(NoteMapper.class);

    public Note entityToDomain(NoteEntity noteEntity)
    {
        logger.info("Mapping entity {} to DTO ", noteEntity);
        return Note.builder()
                .id(noteEntity.getId())
                .text(noteEntity.getText())
                .deleteTimeType(DeleteTimeType.AFTER_READING)
                .localDateTime(noteEntity.getLocalDateTime())
                .build();
    }

    public NoteEntity domainToEntity(Note note)
    {

        logger.info("Mapping DTO {} to entity", note);
        return NoteEntity.builder()
                .id(note.getId())
                .text(note.getText())
                .deleteTimeType(com.axpo.secnotes.infraestructure.entities.DeleteTimeType.AFTER_READING)
                .localDateTime(note.getLocalDateTime())
                .build();
    }
}
