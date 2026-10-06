package impl;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class SystemManager {
    List<Elevator> elevators = new ArrayList<>();
    public static final Long lastFloor = 9L;

    void requestElevator(int floor, RequestDir requestDir) {
        /*
        * If Elevator is idle
        *   take the request, then move in the direction of the maximum requests
        * If elevator is in motion
        *   Keep all the requests in a way that we can scan and fulfill
        * If all the requests for one direction are served
        *   check if other direction's requests are left
        *      if so just flip
        *      else stay idle
        * */
        Elevator elevator = getBestElevator(floor, requestDir);

        var haltRequest = new HaltsRequests(requestDir, floor);
        if (elevator.currentState == ElevatorStates.IDLE) {
            elevator.setCurrentState(requestDir.toElevatorStates());
        }
        elevator.getHaltsRequests().add(haltRequest);

    }

    Elevator getBestElevator(int floor, RequestDir requestDir) {
        /*
         * First the elevator moving in the same direction as we want to go
         * then find the elevator that is closest for us
         * then check if the elevator may have not passed us
         * else just fallback to some idle one
         * */

        List<Elevator> elevators = this.elevators.stream()
                .filter(ele -> ele.equalDir(requestDir)).toList();

        elevators = filterElevatorsStillToReachUs(elevators, requestDir, floor);

        if (elevators.isEmpty()) {
            return getTheDefaultElevator(floor);
        }

        return elevators.get(0);
    }

    Elevator getTheDefaultElevator(int floor) {
        var elevator = this.elevators.stream()
                .filter(ele -> ElevatorStates.IDLE.equals(ele.currentState))
                .min(Comparator.comparingInt(a -> Math.abs(a.currentFloor - floor)))
                .orElse(null);

        if (elevator == null) {
            return this.elevators.get(0);
        }
        return elevator;
    }

    List<Elevator> filterElevatorsStillToReachUs(List<Elevator> elevators, RequestDir requestDir, int floor) {
        if (RequestDir.UP == requestDir) {
            return elevators.stream()
                    .filter(elevator -> elevator.currentFloor <= floor)
                    .sorted(Comparator.comparingInt(
                            (Elevator elevator) -> elevator.currentFloor
                    ).reversed())
                    .toList();
        }

        return elevators.stream()
                .filter(elevator -> elevator.currentFloor >= floor)
                .sorted(Comparator.comparingInt(
                        (Elevator elevator) -> elevator.currentFloor
                ))
                .toList();
    }
}
