package Services;

import Entities.PlayerQueue;
import Entities.Song;

import java.util.Queue;

public interface PlayerStates {
    void play(Song song);
    void resume();
    void play(PlayerQueue playerQueue);
    void pause(double pauseAt);
    void next();
    void previous();
    void loop();
    void makeQue(String name);
    void addSongToQueue(PlayerQueue playerQueue, Song song);
    void removeSongFromQueue(PlayerQueue playerQueue, Song song);
}
