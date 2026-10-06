package impl;

import entities.Machine;
import entities.MachineState;
import entities.Money;
import entities.Order;

public class DispenseState implements VendingMachineState {

    private final Machine machine;

    public DispenseState(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String getName() {
        return MachineState.DISPENSE.name();
    }

    @Override
    public Money refund(Order order) {
        var items = order.getSlotToQuantityMapping();
        int moneyToRefund = items.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getItem().getPrice() * entry.getValue())
                .sum();
        var register = machine.getRegister();


        Money refund = new Money();
        for (var entry : register.getNotes().entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();


            int coinsNeeded = moneyToRefund / denomination;
            int coinsTobeDeducted = Math.min(coinsNeeded, count);

            moneyToRefund = moneyToRefund - (coinsTobeDeducted * denomination);
            register.getNotes().put(denomination, count - coinsTobeDeducted);
            refund.getNotes().put(denomination, coinsTobeDeducted);

            if (register.getNotes().get(denomination) == 0) {
                register.getNotes().remove(denomination);
            }

            machine.setChangeOrRefund(refund);
            machine.setState(MachineState.IDLE);
        }
        return refund;
    }

    @Override
    public Money returnChange(Money money) {
        return machine.getChangeOrRefund();
    }
}
