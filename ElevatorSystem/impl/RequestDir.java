package impl;

public enum RequestDir {
    UP, DOWN;

    ElevatorStates toElevatorStates() {
        if (RequestDir.UP == this) {
            return ElevatorStates.UP;
        }
        return ElevatorStates.DOWN;
    }
}
