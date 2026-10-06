package impl;

import java.util.List;
import java.util.Map;

public class GameManager {
    private Game game;


    void getGame(List<Player> players, Map<Integer, Snake> snakeMap, Map<Integer, Ladder> ladderMap) {
        if (game == null) {
            game = new Game(players, snakeMap, ladderMap);
        }
    }

    void addPlayer(Player player) {
        if (game.getGameState() == GameState.YET_TO_START) {
            game.getPlayers().add(player);
        }
    }

    Player getNextPlayer() {
        return game.getPlayerWithNextTurn();
    }

    void play() {
        Player player = getNextPlayer();
        if (game.getGameState() == GameState.WON) {
            return;
        }
        if (PlayerState.IDLE.equals(player.getState())) {
            return;
        }

        int run = Dice.getRun();

        int finalCell = player.getCurrentCell() + run;
        if(finalCell > 100){
            return;
        }
        player.setCurrentCell(finalCell);

        if(encounteredWin(player)) return;
        encounteredLadder(player);
        encounteredSnake(player);
    }

    boolean encounteredWin(Player player) {
        if (player.getCurrentCell() == 100) {
            game.setGameState(GameState.WON);
            player.setState(PlayerState.WON);
            return true;
        }
        return false;
    }

    void encounteredSnake(Player player) {
        Snake snake = game.snakesMap.get(player.getCurrentCell());
        if (snake != null) {
            snake.eat(player);
        }
    }

    void encounteredLadder(Player player) {
        Ladder ladder = game.ladderMap.get(player.getCurrentCell());
        if (ladder != null) {
            ladder.rideOn(player);
        }
    }
}
