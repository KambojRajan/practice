Requirements
- User should be able to select any [slot]
- User should able to pay with any [money note](given that the note's denomination  >= total order's price)
- Post money insert, [system] must be able to find 
    - That it can dispense all the [items] in their required quantity
    - Also the system should be able to find if it will be able to return correct return money
- If everything passes items must be dispensed
- If any number of items are miss dispensed then we should be able to refund correctly

Entities
## assuming all the getters, setters and constructors exists
- Item
  - String name
  - Money price
  - Slot slot

- Slot
  - Item
  - Integer availableQuantity
  - dispense(Machine machine, int quantity)

- Order
  - Map<Slot, int> slotToQuantityMapping;
  - void addItem(Slot slot);
  - void removeItem(Slot);
  - Money totalPrice
  
- Machine
  - List<Slot> slots;
  - Order currentOrder
  - MachineState state

- Money
  - Map<Integer, Integer> notes;
  - void add(Money money)
  - void sub(Money money)
  
- MachineState
  IDLE, MONEY_INSERTED, DISPENSE

StateMachines
public  interface VendingMachineState
void getName();
default void selectItem(Slot){
    throw UnSupportedOperation(getName() + "operation is not supported")
}
default void insertMoney(Money money){
throw UnSupportedOperation(getName() + "operation is not supported")
}
default void dispense() {
    throw UnSupportedOperation(getName() + "operation is not supported")
}
void refund(Order order){ // entire order failed
    throw UnSupportedOperation(getName() + "operation is not supported")
}

void returnChange(Money money) {
    throw UnSupportedOperation(getName() + "operation is not supported")
}

public class MahcineFactory(){
// a switch based return of 3 implemented states of ther above interface
}

