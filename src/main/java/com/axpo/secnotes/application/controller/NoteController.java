package com.axpo.secnotes.application.controller;

import com.axpo.secnotes.application.service.NoteService;
import com.axpo.secnotes.application.service.exception.NoteNotFoundException;
import com.axpo.secnotes.domain.Note;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Api(tags="Note")
public class NoteController
{

    private final NoteService noteService;

    @PostMapping("/note")
    public UUID create(@RequestBody String text)
    {
        return noteService.create(new Note(text)).getId();
    }

    @GetMapping(value = "/note/{uuid}")
    public String read(@PathVariable UUID uuid)
    {
        Note note = null;
        try
        {
            note = noteService.read(new Note(uuid));
        } catch (NoteNotFoundException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found", e);
        }
        return note.getText();
    }
}
