package impl;

import entities.*;

public class IdleState implements VendingMachineState {

    private final Machine machine;

    public IdleState(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String getName() {
        return MachineState.IDLE.name();
    }

    @Override
    public void selectItem(Spot slot) {
        if (slot.getAvailableQuantity() == 0) return;
        Order order = machine.getCurrentOrder();
        slot.setAvailableQuantity(slot.getAvailableQuantity() - 1);
        order.addItem(slot);
    }

    @Override
    public void insertMoney(Money money) {
        Money totalOrderPrice = machine.getCurrentOrder().getTotalPrice();
        if (totalOrderPrice.lt(money)) {
            return;
        }
        machine.setInsertedMoney(money);
        machine.setState(MachineState.MONEY_INSERTED);
    }
}
