import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;

@Slf4j
public class BookingDbReader {

    public static final int MY_SQL_PORT = 3306;
    public static final String MY_SQL_IP = "127.0.0.1";
    public static final String MY_SQL_DATABASE = "restfulbooker";
    public static final String MY_SQL_HOST =
            // jdbc:mysql://localhost:3306/restfulbooker
            StringUtils.join("jdbc:mysql://", MY_SQL_IP, ":", MY_SQL_PORT, "/");
    public static final String MY_SQL_URL = StringUtils.join(MY_SQL_HOST, MY_SQL_DATABASE);
    public static final String MY_SQL_USERNANE = "root";
    public static final String MY_SQL_PASSWORD = "password";

    public static void main(String[] args) {
        try {
            Connection dbConnection = DriverManager.getConnection(MY_SQL_URL, MY_SQL_USERNANE, MY_SQL_PASSWORD);
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM booking;");
            while (result.next()) {
                long id = result.getLong(1);
                String firstname = result.getString("firstname");
                int totalprice = result.getInt("totalprice");
                boolean depositpaid = result.getBoolean("depositpaid");
                System.out.printf("id: %s\n", id);
                System.out.printf("firstname: %s\n", firstname);
                System.out.printf("totalprice: %s\n", totalprice);
                System.out.printf("depositpaid: %s\n", depositpaid);
            }
            dbConnection.close();
        } catch (SQLException e) {
            log.error("sql connection");
            e.printStackTrace();
        }
    }

}
