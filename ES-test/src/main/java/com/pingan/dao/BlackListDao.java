package com.pingan.dao;


import com.pingan.vo.BlackUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListDao {

    Integer countBlackUsersNum();

    List<BlackUser> queryBlackUsers(BlackUser blackUser);
}
