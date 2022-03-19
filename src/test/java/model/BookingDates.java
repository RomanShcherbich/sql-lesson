package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDates  {

    @Builder.Default
    public String checkin = "2019-12-31";
    @Builder.Default
    public String checkout = "2021-02-23";

}
