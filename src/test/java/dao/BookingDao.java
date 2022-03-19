package dao;

import lombok.extern.slf4j.Slf4j;
import model.Booking;
import org.apache.commons.lang3.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BookingDao extends JdbcDao implements GenericDao<Booking> {

    protected static final String BOOKING_TABLE = "booking";
    protected static final String SELECT_ALL_STATEMENT = StringUtils.join("SELECT * FROM ", BOOKING_TABLE, ";");

    protected static final String SELECT_BY_ID_STATEMENT = String.format("""
            SELECT * FROM FROM %s
            WHERE users.id = ?;
            """, BOOKING_TABLE);
    protected static final String INSERT_STATEMENT = String.format("""
            INSERT INTO %s
            (firstname, lastname, totalprice, depositpaid, additionalneeds, bookingdates_id)
            VALUES(?, ?, ?, ?, ?, ?);
            """, BOOKING_TABLE);

    public BookingDao() {
        super(BOOKING_TABLE);
    }

    @Override
    public Booking create(Booking booking) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_STATEMENT);
//                PreparedStatement ps = connection.prepareStatement(INSERT_ERROR_STATEMENT);
        ) {
            ps.setString(1, booking.getFirstname());
            ps.setString(2, booking.getLastname());
            ps.setInt(3, booking.getTotalPrice());
            ps.setBoolean(4, booking.getDepositPaid());
            ps.setString(5, booking.getAdditionalNeeds());
            ps.setInt(6, 1);
            log.info(String.valueOf(ps));
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(String.format("Can't create booking [%s]", booking));
            log.error(e.getMessage());
        }
        return booking;
    }

    @Override
    public Booking read(int id) {
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public Booking delete(int id) {
        return null;
    }

    @Override
    public List<Booking> listAll() {
        List<Booking> bookingList = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            log.info(SELECT_ALL_STATEMENT);
            ResultSet rs = statement.executeQuery(SELECT_ALL_STATEMENT);
            while (rs.next()) {
                Booking booking =
                        Booking.builder()
                                .id(rs.getLong("id"))
                                .firstname(rs.getString("firstname"))
                                .lastname(rs.getString("lastname"))
                                .totalPrice(rs.getInt("totalPrice"))
                                .depositPaid(rs.getBoolean("depositPaid"))
                                .additionalNeeds(rs.getString("additionalNeeds"))
                                .build();
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            log.error("Can't select users from database");
            log.error(e.getMessage());
        }
        return bookingList;
    }

}
