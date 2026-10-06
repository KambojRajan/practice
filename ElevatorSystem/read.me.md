Requirements
- functional
  - user outside the [elevator] must be able to make a [request]
  - user inside the elevator must be able to select multiple <floor> as [halts]
  - system must handle multiple elevators
  - We will [simulate] the working using step functions
- non-functional
  - elevator selection must be as efficient as possible(this for now is the nearest elevator but can be extendable if time permits)

Entities
- SystemManager
- Elevator
- Request
Enum
- ElevatorStates(UP, DOWN, IDLE)
- RequestDir(UP, DOWN)


Implementation
- must implement the raise request interface
- add things if time permits
