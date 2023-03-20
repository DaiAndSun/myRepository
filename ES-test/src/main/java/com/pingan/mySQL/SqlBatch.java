package com.pingan.mySQL;


import com.pingan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SqlBatch {

    public static void batch(Integer num)  {

        Connection connection = JDBCUtils.getConnection();


        String sql = "insert into blacklist values(null,?,?,?)";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);


        for (int i = 1; i <= num; i++) {
            statement.setString(1,"张" + i);
            statement.setString(2,"1800000000" + i);
            statement.setString(3,"张" + i + "是黑名单用户");

            statement.addBatch();
        }

        int[] ints = statement.executeBatch();

            if (ints.length == num) {
                System.out.println("插入" + num + "条数据成功");
            } else {
                System.out.println("插入数据失败！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,null,null);
        }


    }

}
