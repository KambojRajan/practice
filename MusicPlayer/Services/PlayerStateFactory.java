package Services;

import Entities.Player;
import Entities.States;

public class PlayerStateFactory {

    public PlayerStates getPlayerState(States state, Player player) {
        return switch (state) {
            case PAUSE -> new PauseState(player);
            case PLAYING -> new PlayingState(player);
            default -> new IdleState(player);
        };
    }
}
