package dao;

import db.DbConnectionWithSsh;

import java.sql.Connection;

public class JdbcDao {

    Connection connection = DbConnectionWithSsh.getConnection();
    String table;

    public JdbcDao(String table) {
        this.table = table;
    }

}
