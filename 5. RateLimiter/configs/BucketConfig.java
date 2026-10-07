package configs;

import lombok.Data;

@Data
public class BucketConfig implements LimiterConfig {
    private Long capacity;
    private Long refillRatePerSecond;

    @Override
    public LimiterType getLimiterType() {
        return LimiterType.BUCKET;
    }
}
