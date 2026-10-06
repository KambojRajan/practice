package impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Builder
@Setter
@Getter
public class Ticket {
    String vehicleNumber;
    TicketStatus status;

    Timestamp entryTime;
    Timestamp exitTime;

    Double totalPayableAmount;
}
