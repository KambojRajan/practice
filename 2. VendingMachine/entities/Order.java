package entities;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Order {
    private Map<Slot, Integer> slotToQuantityMapping = new LinkedHashMap<>();
    private Money totalPrice = new Money();

    public void addItem(Slot slot) {
        slotToQuantityMapping.put(slot, slotToQuantityMapping.get(slot) + 1);
    }

    public void removeItem(Slot slot) {
        slotToQuantityMapping.put(slot, slotToQuantityMapping.get(slot) - 1);
        if (slotToQuantityMapping.get(slot) == 0) {
            slotToQuantityMapping.remove(slot);
        }
    }
}
