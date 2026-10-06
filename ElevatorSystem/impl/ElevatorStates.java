package impl;

public enum ElevatorStates {
    IDLE, UP, DOWN;

    public static ElevatorStates flip(ElevatorStates states) {
        if (DOWN == states) {
            return UP;
        } else if (UP == states) {
            return DOWN;
        }
        return IDLE;
    }
}
