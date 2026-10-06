package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerQueue {
    private final String name;
    private final List<Song> songs = new ArrayList<>();
    private List<Integer> playOrder = new ArrayList<>();
    private int position = 0;

    public PlayerQueue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public boolean isEmpty() {
        return songs.isEmpty();
    }

    public Song getCurrentSong() {
        return songs.get(playOrder.get(position));
    }

    public void addSong(Song song) {
        songs.add(song);
        playOrder.add(songs.size() - 1);
    }

    public void removeSong(Song song) {
        int index = songs.indexOf(song);
        if (index == -1) {
            return;
        }
        songs.remove(index);
        playOrder.remove(Integer.valueOf(index));
        for (int i = 0; i < playOrder.size(); i++) {
            int value = playOrder.get(i);
            if (value > index) {
                playOrder.set(i, value - 1);
            }
        }
        if (position >= playOrder.size()) {
            position = 0;
        }
    }

    public Song advanceToNext(boolean loop) {
        if (playOrder.isEmpty()) {
            return null;
        }
        boolean atEnd = position == playOrder.size() - 1;
        if (atEnd && !loop) {
            return getCurrentSong();
        }
        position = (position + 1) % playOrder.size();
        return getCurrentSong();
    }

    public Song rewindToPrevious(boolean loop) {
        if (playOrder.isEmpty()) {
            return null;
        }
        boolean atStart = position == 0;
        if (atStart && !loop) {
            return getCurrentSong();
        }
        position = (position - 1 + playOrder.size()) % playOrder.size();
        return getCurrentSong();
    }

    public void shuffle() {
        Song current = getCurrentSong();
        Collections.shuffle(playOrder);
        position = playOrder.indexOf(songs.indexOf(current));
    }

    public void unshuffle() {
        Song current = getCurrentSong();
        List<Integer> identity = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            identity.add(i);
        }
        playOrder = identity;
        position = songs.indexOf(current);
    }
}
