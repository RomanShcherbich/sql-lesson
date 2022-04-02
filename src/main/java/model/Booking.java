package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private long id;
    @Builder.Default
    private String firstname = "Test firstname";
    @Builder.Default
    private String lastname = "Test lastname";
    @Builder.Default
    private double totalPrice = 101.01;
    @Builder.Default
    private boolean depositPaid = true;
    @Builder.Default
    private String additionalNeeds = "Branch";
    @Builder.Default
    private long bookingDatesId = 1;

}
