package com.axpo.secnotes.architecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.assertEquals;

@Testcontainers
public class RedisContainerTest
{
    @Container
    public GenericContainer mongodb = new GenericContainer(DockerImageName.parse("docker.io/bitnami/mongodb:4.4.1-debian-10-r39")).withExposedPorts(27017);


    @BeforeEach
    public void setUp() {
        String address = mongodb.getHost();
        Integer port = mongodb.getFirstMappedPort();
    }

    @Test
    public void testSimplePutAndGet() {

        assertEquals("example", "retrieved");
    }
}
