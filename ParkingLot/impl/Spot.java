package impl;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spot {
    VehicleType type;
    SpotStatus status;
    String vehicleNumber;

    public boolean isEmpty() {
        return status == SpotStatus.EMPTY;
    }
}
