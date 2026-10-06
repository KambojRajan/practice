package impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    List<Player> players;
    int currentPlayer = -1;
    Map<Integer, Snake> snakesMap;
    Map<Integer, Ladder> ladderMap;
    GameState gameState;

    public Game(List<Player> players, Map<Integer, Snake> snakeMap, Map<Integer, Ladder> ladderMap) {
        this.players = players;
        this.snakesMap = snakeMap;
        this.ladderMap = ladderMap;
        this.gameState = GameState.YET_TO_START;
    }

    public Player getPlayerWithNextTurn() {
        currentPlayer = (currentPlayer + 1) % players.size();
        return players.get(currentPlayer);
    }
}
