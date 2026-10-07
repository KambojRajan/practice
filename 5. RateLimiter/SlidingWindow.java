import configs.SlidingWindowConfig;

import java.sql.Timestamp;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    private final String userId;
    Deque<Timestamp> requests;

    public SlidingWindow(String userId, SlidingWindowConfig config) {
        this.userId = userId;
        this.requests = new LinkedList<>();
    }

    public boolean tryConsume(SlidingWindowConfig config) {
        var now = System.currentTimeMillis();
        var windowMillis = config.getWindowSizeSeconds() * 1000;

        while (!requests.isEmpty() && requests.peekFirst().getTime() < now - windowMillis) {
            requests.pollFirst();
        }

        if (requests.size() >= config.getCapacity()) {
            return false;
        }


        requests.addLast(new Timestamp(now));
        return true;
    }
}
