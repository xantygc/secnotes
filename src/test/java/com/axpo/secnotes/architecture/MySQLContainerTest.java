package com.axpo.secnotes.architecture;

import com.axpo.secnotes.infraestructure.entities.DeleteTimeType;
import com.axpo.secnotes.infraestructure.entities.NoteEntity;
import com.axpo.secnotes.infraestructure.repository.NoteRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@Testcontainers
@SpringBootTest
public class MySQLContainerTest
{
    @Container
    static public MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:8.0.28"))
            .withUsername("sa")
            .withPassword("password")
            .withDatabaseName("secnotes");


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
    }

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void saveOneAndRecovery() {

        NoteEntity note = new NoteEntity(UUID.randomUUID(), "Nota1", DeleteTimeType.AFTER_READING, LocalDateTime.now());
        noteRepository.save(note);
        Assert.assertEquals(1, noteRepository.findAll().size());
    }
}
