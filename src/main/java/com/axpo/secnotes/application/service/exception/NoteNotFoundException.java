package com.axpo.secnotes.application.service.exception;

public class NoteNotFoundException extends RuntimeException
{

    public NoteNotFoundException (String message, Throwable throwable)
    {
        super(message, throwable);
    }

    public NoteNotFoundException(String message)
    {
        super(message, null);
    }
}
