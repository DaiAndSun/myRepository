package com.pingan.esDao;


import com.pingan.vo.BlackUser;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.stereotype.Component;

import java.util.List;

public interface EsListDao {

     void toBulk(List<BlackUser> blackUserList);
}
