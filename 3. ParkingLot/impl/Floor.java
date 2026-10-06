package impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    Integer floorNo;
    Set<Spot> spots;
    VehicleType type;

    public Spot getSpot() {
        return spots.stream().filter(Spot::isEmpty).findFirst().orElse(new Spot(type, SpotStatus.OCCUPIED, null));
    }
}
