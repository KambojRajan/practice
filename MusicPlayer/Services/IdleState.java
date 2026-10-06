package Services;

import Entities.Player;
import Entities.PlayerQueue;
import Entities.Song;
import Entities.States;

public class IdleState implements PlayerStates {

    private final Player player;

    public IdleState(Player player) {
        this.player = player;
    }

    @Override
    public void play(Song song) {
        player.setCurrentSong(song);
        player.setPlayingState(States.PLAYING);
    }

    @Override
    public void play(PlayerQueue queue) {
        player.setQueue(queue);
        player.setCurrentSong(queue.getCurrentSong());
        player.setPlayingState(States.PLAYING);
    }

    @Override
    public void resume() {
        if (player.getCurrentSong() == null) {
            return;
        }
        player.setPlayingState(States.PLAYING);
    }

    @Override
    public void pause(double pauseAt) {
    }

    @Override
    public void next() {
        PlayerQueue queue = player.getQueue();
        if (queue == null || queue.isEmpty()) {
            return;
        }
        player.setCurrentSong(queue.advanceToNext(player.isLoop()));
        player.setPlayingState(States.PLAYING);
    }

    @Override
    public void previous() {
        PlayerQueue queue = player.getQueue();
        if (queue == null || queue.isEmpty()) {
            return;
        }
        player.setCurrentSong(queue.rewindToPrevious(player.isLoop()));
        player.setPlayingState(States.PLAYING);
    }
}
