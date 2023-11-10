package com.elasticsearch.estudo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class VariavelConfiguration {

    private final Environment environment;

    public VariavelConfiguration(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        String url = environment.getProperty("elasticsearch.url");;
        Integer porta = Integer.valueOf(Objects.requireNonNull(environment.getProperty("elasticsearch.porta")));

        RestClient restClient = RestClient.builder(new HttpHost(url, porta)).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }


}
