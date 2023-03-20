package com.pingan.jobs;

import com.pingan.dao.BlackListDao;
import com.pingan.esDao.EsListDao;
import com.pingan.vo.BlackUser;
import io.netty.util.concurrent.CompleteFuture;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;


@Service
public class EsBlackListJob {

    @Resource
    private RestHighLevelClient esClient;

    @Resource
    private BlackListDao blackListDao;

    @Resource
    private EsListDao esListDao;

    private static final Integer PAGE_SIZE = 200;

    public void esJob(){

        //查询数据总条数
        Integer usersNum = blackListDao.countBlackUsersNum();

        //计算页数
        int totalPages = (usersNum % PAGE_SIZE == 0) ? (usersNum / PAGE_SIZE) : (usersNum / PAGE_SIZE + 1);

        for (int i = 1; i <= totalPages; i++) {

            Integer currentPage = i;

            //开启异步任务
            CompletableFuture.runAsync(() -> {

                //获取当前线程名
                String name = Thread.currentThread().getName();

                //计算startNum(分页开始数)
                Integer startNum = (currentPage - 1) * PAGE_SIZE;

                BlackUser blackUser = new BlackUser();
                blackUser.setStartNum(startNum);
                blackUser.setPageSize(PAGE_SIZE);

                //查询数据库
                List<BlackUser> blackUsers = blackListDao.queryBlackUsers(blackUser);

                System.out.println("blackUsers = " + blackUsers.get(0));

                //插入es
                esListDao.toBulk(blackUsers);

            }, Executors.newFixedThreadPool(10));
        }




    }
}
