package db;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DbConnection {

    private static Connection dbConnection = null;

    protected static final String MY_SQL_PORT = "3306";
    protected static final String MY_SQL_HOST = StringUtils.join("jdbc:mysql://0.0.0.0:", MY_SQL_PORT, "/");
    protected static final String MY_SQL_DATABASE = "restfulbooker";
    protected static final String MY_SQL_CONNECTION_URL = StringUtils.join(MY_SQL_HOST, MY_SQL_DATABASE);
    protected static final String MY_SQL_USERNAME = "root";
    protected static final String MY_SQL_PASSWORD = "password";

    public static Connection getConnection() {
        if (dbConnection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbConnection = DriverManager.getConnection(MY_SQL_CONNECTION_URL, MY_SQL_USERNAME, MY_SQL_PASSWORD);
            } catch (ClassNotFoundException e) {
                log.error("Can't find class for com.mysql.cj.jdbc.Driver");
                throw new RuntimeException(e);
            } catch (SQLException e) {
                log.error("Can't create connection to crud_jsp_jdbc_demo database");
                throw new RuntimeException(e);
            }
        }
        return dbConnection;
    }

}
