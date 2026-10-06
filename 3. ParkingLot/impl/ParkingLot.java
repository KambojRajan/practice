package impl;

import java.util.Map;
import java.util.TreeSet;

public class ParkingLot {
    Map<VehicleType, TreeSet<Floor>> floors;
    Map<Ticket, Spot> tickets;

    public synchronized Spot getSpot(VehicleType vehicleType) {
        Floor floor = floors.get(vehicleType).stream()
                .filter(f -> f.getSpot().isEmpty())
                .findFirst()
                .orElse(null);

        if (floor == null) {
            return null;
        }
        return floor.getSpot();
    }
}
