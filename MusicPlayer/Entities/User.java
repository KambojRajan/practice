package Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class User {
    private Map<String, PlayerQueue> playerQueues = new HashMap<>();

    public PlayerQueue createQueue(String name) {
        if (playerQueues.containsKey(name)) {
            throw new IllegalArgumentException("Queue by this name already exists");
        }
        PlayerQueue queue = new PlayerQueue(name);
        playerQueues.put(name, queue);
        return queue;
    }
}
