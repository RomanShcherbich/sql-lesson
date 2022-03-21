import dao.BookingDao;
import db.MySqlConnection;
import lombok.extern.slf4j.Slf4j;
import model.Booking;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class BookingDbReader3 {

    public static void main(String[] args) {
        BookingDao bookingDao = new BookingDao();
//        System.out.println(bookingDao.readAll());
        bookingDao.create(new Booking());
        System.out.println(ArrayUtils.toString(bookingDao.readAll().toArray()).replaceAll("\\),", "\n"));

        System.out.println(bookingDao.read(2));
    }

}
