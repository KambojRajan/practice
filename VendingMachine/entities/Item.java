package entities;

import lombok.Data;

@Data
public class Item {
    private String name;
    private Integer price;
    private Slot slot;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
