package impl;

import entities.Machine;
import entities.MachineState;

public class MachineFactory {
    public VendingMachineState getState(MachineState state, Machine machine) {
        return switch (state) {
            case IDLE -> new IdleState(machine);
            case MONEY_INSERTED -> new MoneyInsertedState(machine);
            case DISPENSE -> new DispenseState(machine);
        };
    }
}
