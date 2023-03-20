package com.pingan.es;


import com.pingan.vo.BlackUser;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.Index;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddIndexRequests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public static List<IndexRequest> add(List<BlackUser> blackUserList) {

        List<IndexRequest> indexRequestList =new ArrayList<>();

        for (BlackUser blackUser : blackUserList) {

            //es文档id
            String esId = blackUser.getId() + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 4);

            IndexRequest request = new IndexRequest("blacklist", DocWriteRequest.OpType.INDEX.toString(), esId);

            indexRequestList.add(request);

        }



        return indexRequestList;
    }


}
