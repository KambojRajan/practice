package impl;

import impl.payments.PaymentStrategy;
import impl.payments.config.PricingConfig;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
public class Manager {
    private ParkingLot parkingLot;
    private PricingConfig pricingConfig;
    private List<PaymentStrategy> paymentStrategies;

    public void registerVehicle(VehicleType type, String vehicleId) {
        Spot spot = parkingLot.getSpot(type);
        if (spot == null) {
            System.out.println("No parking spot available for type " + type);
            return;
        }
        spot.setStatus(SpotStatus.REQUESTED);
        spot.setVehicleNumber(vehicleId);
    }

    public Ticket takeOut(Ticket ticket) {
        ticket.setExitTime(new Timestamp(System.currentTimeMillis()));
        ticket.setStatus(TicketStatus.PAYMENT_PENDING);

        boolean paymentStatus = pay(pricingConfig, ticket);
        if (!paymentStatus) {
            // can be improved with better error
            return null;
        }

        Spot spot = parkingLot.tickets.get(ticket);
        spot.setStatus(SpotStatus.EMPTY);

        return ticket;
    }

    boolean pay(PricingConfig config, Ticket ticket) {
        for (PaymentStrategy paymentStrategy : paymentStrategies) {
            if (paymentStrategy.shouldRun(config)) {
                return paymentStrategy.pay(config, ticket);
            }
        }
        return false;
    }

    public void cancelParking(Spot spot) {
        // only works in Spot status as Requested
        if (spot.getStatus() != SpotStatus.REQUESTED) {
            return;
        }
        spot.setStatus(SpotStatus.EMPTY);
        spot.setVehicleNumber(null);
    }

    public Ticket getTicket(Spot spot) {
        Ticket ticket = Ticket.builder()
                .vehicleNumber(spot.getVehicleNumber())
                .status(TicketStatus.ISSUED)
                .entryTime(new Timestamp(System.currentTimeMillis()))
                .build();

        parkingLot.tickets.put(ticket, spot);
        spot.setStatus(SpotStatus.OCCUPIED);

        return ticket;
    }
}
