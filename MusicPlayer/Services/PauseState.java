package Services;

import Entities.Player;
import Entities.PlayerQueue;
import Entities.Song;
import Entities.States;

public class PauseState implements PlayerStates {

    private final Player player;

    public PauseState(Player player) {
        this.player = player;
    }

    @Override
    public void play(Song song) {
        if (song.equals(player.getCurrentSong())) {
            return;
        }
        player.setCurrentSong(song);
        player.setPlayingState(States.PLAYING);
    }

    @Override
    public void play(PlayerQueue queue) {
        player.setQueue(queue);
        Song songToPlay = queue.getCurrentSong();
        player.setCurrentSong(songToPlay);
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
        player.setPlayingState(States.PAUSE);
    }

    @Override
    public void previous() {
        PlayerQueue queue = player.getQueue();
        if (queue == null || queue.isEmpty()) {
            return;
        }
        player.setCurrentSong(queue.rewindToPrevious(player.isLoop()));
        player.setPlayingState(States.PAUSE);
    }
}
