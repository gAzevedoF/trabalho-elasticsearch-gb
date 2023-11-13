package com.elasticsearch.estudo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
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

//        Conexão local:
//        RestClient restClient = RestClient.builder(new HttpHost(url, porta)).build();

        RestClient restClient = RestClient
                .builder(HttpHost.create(url))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + "SmFRRHhvc0J6ejZjX1JqXzlJZ0U6Q1d2YlNRUDNUdTJ5a0R2UHVNTFJfdw==")
                })
                .build();


        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }


}
