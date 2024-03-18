package com.pure.config;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Slf4j
@Configuration
@EnableConfigurationProperties(EsConfigProperties.class)
public class EsConfig {
    @Bean
    public ElasticsearchClient elasticsearchClient(EsConfigProperties properties) {
        RestClient restClient = getRestClient(properties);

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

    @Bean
    public ElasticsearchAsyncClient elasticsearchAsyncClient(EsConfigProperties properties) {
        RestClient restClient = getRestClient(properties);

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchAsyncClient(transport);
    }



    private RestClient getRestClient(EsConfigProperties properties) {
        SSLContext sslContext = TransportUtils
                .sslContextFromCaFingerprint(properties.getFingerprint());

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword())
        );

        RestClient restClient = RestClient
                .builder(new HttpHost(properties.getHost(), properties.getPort(), properties.getScheme()))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProv)
                ).build();
        return restClient;
    }


}
