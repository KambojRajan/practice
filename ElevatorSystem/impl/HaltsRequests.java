package impl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HaltsRequests {
    RequestDir requestDir;
    int floor;

    boolean equalDir(ElevatorStates currentState) {
        if (ElevatorStates.UP == currentState && RequestDir.UP == requestDir) {
            return true;
        }
        return ElevatorStates.DOWN == currentState && RequestDir.DOWN == requestDir;
    }
}
