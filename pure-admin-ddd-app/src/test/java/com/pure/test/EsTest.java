package com.pure.test;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.IOException;

@Slf4j
public class EsTest {
    public static void main(String[] args) throws IOException {
       /* // URL and API key
        String serverUrl = "https://10.64.16.173:9200";
        String apiKey = "ZWRPa1FJNEJNajRYZnpYQ2FGT2Q6WDUzdzFlTzhSdC1wZHlUQW05Vko0UQ==";

        // Create the low-level client
        Header[] headers = new Header[]{new BasicHeader("Authorization", "ApiKey " + apiKey)};
        RestClient restClient = RestClient.builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(headers).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient esClient = new ElasticsearchClient(transport);

        esClient.indices().create(c -> c
                .index("products")
        );*/

        String fingerprint = "F7:D8:DD:0C:18:E9:86:07:8B:4C:D3:C6:07:2C:AB:F3:D5:A1:26:12:E2:A7:2F:F5:20:92:F7:3C:25:2A:B5:E2";

        SSLContext sslContext = TransportUtils
                .sslContextFromCaFingerprint(fingerprint);

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456")
        );

        RestClient restClient = RestClient
                .builder(new HttpHost("10.64.16.173", 9200, "https"))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProv)
                )
                .build();

        // Create the transport and the API client
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        //ElasticsearchClient client = new ElasticsearchClient(transport);

        /*client.indices().create(c -> c
                .index("products")
        );*/

        // Asynchronous non-blocking client
        /*if (client.exists(b -> b.index("products").id("foo")).value()) {
            log.info("product exists");
        }*/

        // Asynchronous non-blocking client
        ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);

        asyncClient
                .exists(b -> b.index("products").id("foo"))
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        log.error("Failed to index", exception);
                    } else {
                        log.info("Product exists");
                    }
                });
    }
}
