package entities;

import lombok.Data;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Data
public class Money {
    private Map<Integer, Integer> notes = new TreeMap<>(Comparator.reverseOrder());

    public Money copy() {
        return new Money(notes);
    }

    public Money() {
    }

    public Money(Map<Integer, Integer> notes) {
        this.notes.putAll(notes);
    }

    public void add(Money money) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void sub(Money money) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int value() {
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : this.notes.entrySet()) {
            int denominator = entry.getKey();
            int count = entry.getValue();
            value += (denominator * count);
        }

        return value;
    }

    public boolean lt(Money money) {
        if (money == null) {
            return false;
        }
        return money.value() >= this.value();
    }
}
