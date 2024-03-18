package com.pure.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.SSLContext;
import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    @Autowired
    private ElasticsearchClient esClient;
    @Test
    public  void testIndex() throws IOException {
        Product product = new Product("bk-2", "City bike", 124.0);
        IndexResponse response = esClient.index(i -> i
                .index("products")
                .id(product.getSku())
                .document(product)
        );
        log.info("Indexed with version " + response.version());
    }
}
