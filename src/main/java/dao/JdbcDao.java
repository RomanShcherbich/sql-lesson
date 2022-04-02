package dao;

import db.MySqlConnection;

import java.sql.Connection;

import static org.apache.commons.lang3.StringUtils.join;

public abstract class JdbcDao {

    private String table;
    protected Connection connection;
    protected String selectQuery;

    public JdbcDao(String table) {
        this.table = table;
        this.connection = new MySqlConnection().get();
        this.selectQuery = join("SELECT * FROM ", table, ";");
    }

}
