import db.MySqlConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;

@Slf4j
public class BookingDbReader2 {

    public static void main(String[] args) {
        try {
            Connection dbConnection = new MySqlConnection().get();
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
