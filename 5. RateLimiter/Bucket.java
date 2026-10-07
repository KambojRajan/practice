import configs.BucketConfig;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Bucket {
    private final String userId;
    private Timestamp lastRefillTime;
    private Long tokens;

    public Bucket(String userId, BucketConfig config) {
        this.userId = userId;
        this.lastRefillTime = new Timestamp(System.currentTimeMillis());
        this.tokens = config.getCapacity();
    }

    void refillIfDue(BucketConfig config) {
        long secondsLapsed = (System.currentTimeMillis() - getLastRefillTime().getTime()) / 1000;
        long tokensToAdd = secondsLapsed * config.getRefillRatePerSecond();

        tokens = Math.min(tokens + tokensToAdd, config.getCapacity());
    }

    public boolean tryConsume() {
        if (tokens <= 0) return false;
        tokens--;
        this.lastRefillTime = new Timestamp(System.currentTimeMillis());
        return true;
    }
}
