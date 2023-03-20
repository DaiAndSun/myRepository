package com.pingan.esDao.impl;

import com.pingan.es.AddIndexRequests;
import com.pingan.es.EsClient;
import com.pingan.esDao.EsListDao;
import com.pingan.vo.BlackUser;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsListImpl implements EsListDao {

    @Autowired
    EsClient esClient;

    @Autowired
    BulkProcessor bulkProcessor;

    /**
     * 批量添加数据到es
     *
     * @param blackUserList
     */
    @Override
    public void toBulk(List<BlackUser> blackUserList) {

        List<IndexRequest> indexRequests = AddIndexRequests.add(blackUserList);

        try {
            for (IndexRequest indexRequest : indexRequests) {
                bulkProcessor.add(indexRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bulkProcessor.flush();
        }


    }
}
