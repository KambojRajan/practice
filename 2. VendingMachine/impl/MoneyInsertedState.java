package impl;

import entities.*;

import java.util.Map;

public class MoneyInsertedState implements VendingMachineState {

    private final Machine machine;

    public MoneyInsertedState(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String getName() {
        return MachineState.MONEY_INSERTED.name();
    }

    @Override
    public void dispense() {
        Order order = machine.getCurrentOrder();
        Map<Spot, Integer> items = order.getSlotToQuantityMapping();

        for (Map.Entry<Spot, Integer> entry : items.entrySet()) {
            Spot slot = entry.getKey();
            int quantity = entry.getValue();
            slot.dispense(machine, quantity);
        }

        deductMoney(items);
    }

    private void deductMoney(Map<Spot, Integer> items) {
        int totalPrice = items.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getItem().getPrice() * entry.getValue())
                .sum(); // 10 rupee

        Money insertedMoney = machine.getInsertedMoney(); // 50 X 1

        var tempRegister = machine.getRegister().copy();
        tempRegister.getNotes().putAll(insertedMoney.getNotes()); // 100 X 4 and 50 X 1

        for (var entry : tempRegister.getNotes().entrySet()) {
            int denomination = entry.getKey(); // 50
            int count = entry.getValue(); // 1


            int coinsNeeded = totalPrice / denomination; // 10/50 ==> 0
            int coinsTobeDeducted = Math.min(coinsNeeded, count); // ==> 0

            totalPrice = totalPrice - (coinsTobeDeducted * denomination); // 10
            insertedMoney.getNotes().put(denomination, count - coinsTobeDeducted);

            if (insertedMoney.getNotes().get(denomination) == 0) {
                insertedMoney.getNotes().remove(denomination);
            }
        }
        if (totalPrice >= 0) {
            throw new UnsupportedOperationException("Unable to return change");
        }

        machine.setChangeOrRefund(insertedMoney);
        machine.setState(MachineState.DISPENSE);
    }
}
