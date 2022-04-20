package com.axpo.secnotes.infraestructure.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Note")
@Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class NoteEntity
{
    @Id
    private UUID id;
    private String text;
    private DeleteTimeType deleteTimeType;
    private LocalDateTime localDateTime;

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteEntity that = (NoteEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode ()
    {
        return getClass().hashCode();
    }
}
