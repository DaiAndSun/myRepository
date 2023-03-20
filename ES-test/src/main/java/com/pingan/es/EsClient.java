package com.pingan.es;


import com.mysql.cj.util.TimeUtil;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class EsClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Bean
    public BulkProcessor bulkProcessor() {

        BulkProcessor builder = BulkProcessor.builder((request, actionListener) -> {
            restHighLevelClient.bulkAsync(request, RequestOptions.DEFAULT, actionListener);
        }, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                System.out.println("es bulk执行开始");
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {

                if (failure != null) {
                    System.err.println("es执行失败" + failure);
                }

            }
        }).setBulkActions(1000)
                .setFlushInterval(TimeValue.timeValueSeconds(10L))
                .build();

        return builder;
    }
}
