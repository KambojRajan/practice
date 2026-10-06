package entities;

import lombok.Data;

import java.util.List;

@Data
public class Machine {
    private List<Slot> slots;
    private Order currentOrder = new Order();
    private MachineState state = MachineState.IDLE;
    private Money InsertedMoney = new Money();
    private Money changeOrRefund = new Money();
    private Money register = new Money();

    public Machine(List<Slot> slots) {
        this.slots = slots;
    }
}
