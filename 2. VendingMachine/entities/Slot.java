package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slot {
    private Item item;
    private int availableQuantity;

    public Slot(Item item, int availableQuantity) {
        this.item = item;
        this.availableQuantity = availableQuantity;
        item.setSlot(this);
    }

    public boolean dispense(Machine machine, int quantity) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
