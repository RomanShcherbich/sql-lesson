package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDates {

    @Builder.Default
    private String checkin = "2022-01-01";
    @Builder.Default
    private String checkout = "2022-02-02";

}
