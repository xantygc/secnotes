package com.axpo.secnotes.application.port.usecases;

import com.axpo.secnotes.domain.Note;

public interface ReadNoteUseCase
{
    Note read(Note note);
    Boolean delete(Note note);
}
