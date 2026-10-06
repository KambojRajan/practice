package impl;

import lombok.Data;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Data
public class Elevator {
    Set<HaltsRequests> haltsRequests = new HashSet<>();
    ElevatorStates currentState;
    int currentFloor;

    void addHaltRequest(HaltsRequests request) {
        haltsRequests.add(request);
    }


    int getNextFloor() {
        if (currentState == ElevatorStates.UP) {
            return Math.toIntExact((currentFloor + 1) % SystemManager.lastFloor);
        } else {
            return Math.toIntExact((currentFloor - 1 + SystemManager.lastFloor) % SystemManager.lastFloor);
        }
    }

    void step() {
        HaltsRequests requestToProcess = getNextRequestForGivenDir();
        if (requestToProcess == null) {
            currentState = ElevatorStates.flip(currentState);
            return;
        }
        if(this.getCurrentFloor() == requestToProcess.getFloor()){
            haltsRequests.remove(requestToProcess);
            return;
        }
        this.setCurrentFloor(getNextFloor());
    }

    boolean equalDir(RequestDir requestDir) {
        if (ElevatorStates.UP == currentState && RequestDir.UP == requestDir) return true;
        return ElevatorStates.DOWN == currentState && RequestDir.DOWN == requestDir;
    }

    HaltsRequests getNextRequestForGivenDir() {
        if (currentState == ElevatorStates.UP)
            return haltsRequests.stream().toList()
                    .stream().filter(req -> req.equalDir(currentState))
                    .min(Comparator.comparingInt(HaltsRequests::getFloor))
                    .orElse(null);
        else
            return haltsRequests.stream().toList()
                    .stream().filter(req -> req.equalDir(currentState))
                    .max(Comparator.comparingInt(HaltsRequests::getFloor))
                    .orElse(null);
    }
}
