package Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private Song currentSong;
    private PlayerQueue queue;
    private States playingState;
    private User currentUser;
    private boolean shuffle;
    private boolean loop;

    @Getter
    private static final Player instance = new Player();

    private Player() {
        this.currentSong = null;
        this.queue = null;
        this.playingState = States.IDLE;
        this.shuffle = false;
        this.loop = false;
    }
}
