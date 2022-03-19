package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {

    private long id;
    private String firstname;
    private String lastname;
    private int totalPrice;
    private Boolean depositPaid;
    private BookingDates bookingDates;
    private String additionalNeeds;

}
