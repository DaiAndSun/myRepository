package com.pingan.controller;


import com.pingan.esDao.EsListDao;
import com.pingan.jobs.EsBlackListJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EsController {

    @Autowired
    private EsBlackListJob esBlackListJob;

    @RequestMapping("/dbToEs")
    public String dbToEs() {

        esBlackListJob.esJob();


        return "success";
    }
}
