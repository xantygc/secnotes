package com.axpo.secnotes;

import com.axpo.secnotes.configuration.SpringFoxConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(SpringFoxConfiguration.class)
@RunWith(SpringRunner.class)
class SecnotesApplicationTests
{
    @Autowired
    private Docket api;

    @Test
    void testConfiguration()
    {
        assertEquals(DocumentationType.OAS_30, api.getDocumentationType());
    }
}