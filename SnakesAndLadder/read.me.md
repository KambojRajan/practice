Requirements
- N [Players] should be able to take part in the [Game]
- visually 2D, but actually a linear 100 <cell>
- Any Player that will reach the 100th cell first will win
- Once someone wins the game ends
- Order of the player should be maintained correctly and who will play next should be O(1)
- [Dice] can be controlled but for sake of simplicity it will be a simple random function
- There will be [Snakes] and [Ladders] on static cell locations


Entities
- Game
  - List<Player> players;
  - List<Snakes> snakes;
  - List<Ladder> laders;
  - step()
 

- Player
  - int currentCell;
  - PlayerState state;
  - play()
  - step()
- Dice
  - int run;
- GameManager
- PlayerState
  TURN, IDLE

- Obstacles
  - int start;
  - int end;
- Snakes implement Obstacles
  - void eat(Player player)
- Ladder implement Obstacles
  - rideOn(Player player)

