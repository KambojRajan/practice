package Services;

import Entities.Player;
import Entities.PlayerQueue;
import Entities.Song;
import Entities.States;

public class PlayingState implements PlayerStates {

    private final Player player;

    public PlayingState(Player player) {
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
        if (songToPlay.equals(player.getCurrentSong())) {
            return;
        }
        player.setCurrentSong(songToPlay);
        player.setPlayingState(States.PLAYING);
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause(double pauseAt) {
        player.getCurrentSong().setStart(pauseAt);
        player.setPlayingState(States.PAUSE);
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
