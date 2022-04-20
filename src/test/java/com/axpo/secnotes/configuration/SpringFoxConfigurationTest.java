package com.axpo.secnotes.configuration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringFoxConfiguration.class)
class SpringFoxConfigurationTest
{

    @Autowired
    private Docket api;

    @Test
    void testConfiguration()
    {
        assertEquals(DocumentationType.OAS_30, api.getDocumentationType());
    }



}