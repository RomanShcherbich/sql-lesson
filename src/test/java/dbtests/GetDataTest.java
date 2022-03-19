package dbtests;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.sql.*;

public class GetDataTest {

    @Test
    public void selectBookingTest() {
        Connection dbConnection;
        Statement statement;
        ResultSet resultSet;
        String port = "3306";
        String url = "jdbc:mysql://0.0.0.0:" + port + "/restfulbooker";
        String username = "root";
        String password = "password";
        try {
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM booking");


            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                System.out.println(StringUtils.join("id: ", id));
                String firstname = resultSet.getString("firstname");
                System.out.println(StringUtils.join("firstname: ", firstname));
                String lastname = resultSet.getString("lastname");
                System.out.println(StringUtils.join("lastname: ", lastname));
                int totalprice = resultSet.getInt("totalprice");
                System.out.println(StringUtils.join("totalprice: ", totalprice));
            }

            resultSet.close();
            statement.close();
            dbConnection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
