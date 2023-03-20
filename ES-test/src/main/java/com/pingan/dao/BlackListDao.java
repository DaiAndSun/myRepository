package com.pingan.dao;


import com.pingan.vo.BlackUser;

import java.util.List;

public interface BlackListDao {

    Integer countBlackUsersNum();

    List<BlackUser> queryBlackUsers(BlackUser blackUser);
}
