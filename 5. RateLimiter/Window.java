import configs.BucketConfig;
import configs.WindowConfig;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class Window {
    private final String userId;
    private Timestamp start;
    private Long requestCount;


    public Window(String userId, WindowConfig config) {
        this.userId = userId;
        this.start = Timestamp.from(Instant.now());
        this.requestCount = config.getRequestsPerWindow();
    }

    void reset(WindowConfig config) {
        long end = start.getTime() + config.getWindowSizeSeconds() * 1000;
        if (System.currentTimeMillis() >= end) {
            this.start = Timestamp.from(Instant.now());
            this.requestCount = config.getRequestsPerWindow();
        }
    }

    public boolean tryConsume() {
        if (requestCount <= 0) {
            return false;
        }
        requestCount--;
        return true;
    }
}
