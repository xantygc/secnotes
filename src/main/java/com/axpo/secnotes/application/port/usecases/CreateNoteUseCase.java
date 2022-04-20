package com.axpo.secnotes.application.port.usecases;

import com.axpo.secnotes.domain.Note;

public interface CreateNoteUseCase
{
    Note create (Note note);
}
