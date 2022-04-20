package com.axpo.secnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration(exclude = {SolrAutoConfiguration.class, SpringApplicationAdminJmxAutoConfiguration.class, ActiveMQAutoConfiguration.class, ElasticsearchRepositoriesAutoConfiguration.class})
public class SecnotesApplication
{

    public static void main (String[] args)
    {
        SpringApplication.run(SecnotesApplication.class, args);
    }

}
