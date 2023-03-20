package com.pingan.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESClient {

    @Bean
    public RestHighLevelClient highLevelClient() {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.20.128", 9200, "http")));

        return client;

    }


}
