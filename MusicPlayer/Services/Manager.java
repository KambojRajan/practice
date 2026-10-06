package Services;

import Entities.Player;
import Entities.PlayerQueue;
import Entities.Song;
import Entities.User;

public class Manager {

    private final Player player = Player.getInstance();
    private final PlayerStateFactory stateFactory = new PlayerStateFactory();

    public void setCurrentUser(User user) {
        player.setCurrentUser(user);
    }

    public void play(Song song) {
        currentState().play(song);
    }

    public void play(PlayerQueue queue) {
        currentState().play(queue);
    }

    public void resume() {
        currentState().resume();
    }

    public void pause(double pauseAt) {
        currentState().pause(pauseAt);
    }

    public void next() {
        currentState().next();
    }

    public void previous() {
        currentState().previous();
    }

    public void toggleLoop() {
        player.setLoop(!player.isLoop());
    }

    public void toggleShuffle() {
        boolean next = !player.isShuffle();
        player.setShuffle(next);
        PlayerQueue queue = player.getQueue();
        if (queue != null) {
            if (next) {
                queue.shuffle();
            } else {
                queue.unshuffle();
            }
        }
    }

    public PlayerQueue makeQueue(String name) {
        return player.getCurrentUser().createQueue(name);
    }

    public void addSongToQueue(PlayerQueue queue, Song song) {
        queue.addSong(song);
    }

    public void removeSongFromQueue(PlayerQueue queue, Song song) {
        queue.removeSong(song);
    }

    private PlayerStates currentState() {
        return stateFactory.getPlayerState(player.getPlayingState(), player);
    }
}
