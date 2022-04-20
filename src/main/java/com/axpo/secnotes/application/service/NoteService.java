package com.axpo.secnotes.application.service;

import com.axpo.secnotes.application.port.usecases.CreateNoteUseCase;
import com.axpo.secnotes.application.port.usecases.ReadNoteUseCase;
import com.axpo.secnotes.application.service.exception.NoteNotFoundException;
import com.axpo.secnotes.domain.Note;
import com.axpo.secnotes.infraestructure.entities.NoteEntity;
import com.axpo.secnotes.infraestructure.mapper.NoteMapper;
import com.axpo.secnotes.infraestructure.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService implements CreateNoteUseCase, ReadNoteUseCase
{

    private final static Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;



    @Override
    public Note create (Note note)
    {
        logger.info("Starting to create new Note [{}]", note);
        NoteEntity noteEntity = noteRepository.save(noteMapper.domainToEntity(note));
        logger.info("Finished to create new Note with id {}", noteEntity.getId());
        return noteMapper.entityToDomain(noteEntity);
    }

    @Override
    public Note read (Note note) throws NoteNotFoundException
    {
        logger.info("Starting to read note with id [{}]", note.getId());
        Optional<NoteEntity> noteEntity = noteRepository.findById(note.getId());
        if(noteEntity.isEmpty())
        {
            throw new NoteNotFoundException("Note with id ["+ note.getId() +"] not found");
        }
        logger.info("Finished to find note with id [{}]", note.getId());
        return noteMapper.entityToDomain(noteEntity.get());
    }

    @Override
    public Boolean delete (Note note)
    {
        logger.info("Starting to delete note with id [{}]", note.getId());
        if(noteRepository.existsById(note.getId()))
        {
            noteRepository.deleteById(note.getId());
            logger.info("Finished to delete note with id [{}]", note.getId());
            return Boolean.TRUE;
        }
        throw new NoteNotFoundException("Note with id ["+ note.getId() +"] not found");
    }


}
