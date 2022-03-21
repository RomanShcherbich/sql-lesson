package db;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.apache.commons.lang3.StringUtils.join;

@Slf4j
public class MySqlConnection {

    public static final int MY_SQL_PORT = 3306;
    public static final String MY_SQL_IP = "127.0.0.1";
    public static final String MY_SQL_DATABASE = "restfulbooker";
    public static final String MY_SQL_HOST = join("jdbc:mysql://", MY_SQL_IP, ":", MY_SQL_PORT, "/");
    public static final String MY_SQL_URL = join(MY_SQL_HOST, MY_SQL_DATABASE);
    public static final String MY_SQL_USERNANE = "root";
    public static final String MY_SQL_PASSWORD = "password";

   private Connection dbConnection = null;

    public Connection get() {
        if (dbConnection == null) {
            try {
                dbConnection = DriverManager.getConnection(MY_SQL_URL, MY_SQL_USERNANE, MY_SQL_PASSWORD);
            } catch (SQLException e) {
                log.error(join("SQL connection failed: ", MY_SQL_URL));
                e.printStackTrace();
            }
        }
        return dbConnection;
    }

    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            log.error("SQL connection cannot be closed.");
            e.printStackTrace();
        }
    }

}
