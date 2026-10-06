package impl;

import entities.Machine;
import entities.Money;
import entities.Slot;

public class VendingMachineManager {

    private final Machine machine;
    private final MachineFactory stateFactory = new MachineFactory();

    public VendingMachineManager(Machine machine) {
        this.machine = machine;
    }

    public void selectItem(Slot slot) {
        currentState().selectItem(slot);
    }

    public void insertMoney(Money money) {
        currentState().insertMoney(money);
    }

    public void dispense() {
        currentState().dispense();
    }

    private VendingMachineState currentState() {
        return stateFactory.getState(machine.getState(), machine);
    }
}
