package com.axpo.secnotes.infraestructure.repository;

import com.axpo.secnotes.infraestructure.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, UUID>
{
}
