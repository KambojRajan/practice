package impl;

import entities.Money;
import entities.Order;
import entities.Slot;

public interface VendingMachineState {
    String getName();

    default void selectItem(Slot slot) {
        throw new UnsupportedOperationException(getName() + " operation is not supported");
    }

    default void insertMoney(Money money) {
        throw new UnsupportedOperationException(getName() + " operation is not supported");
    }

    default void dispense() {
        throw new UnsupportedOperationException(getName() + " operation is not supported");
    }

    default Money refund(Order order) {
        throw new UnsupportedOperationException(getName() + " operation is not supported");
    }

    default Money returnChange(Money money) {
        throw new UnsupportedOperationException(getName() + " operation is not supported");
    }
}
