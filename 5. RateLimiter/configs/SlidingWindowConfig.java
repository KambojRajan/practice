package configs;

import lombok.Data;

@Data
public class SlidingWindowConfig implements LimiterConfig {
    private Long requestsPerWindow;
    private Long windowSizeSeconds;
    private Long capacity;

    @Override
    public LimiterType getLimiterType() {
        return LimiterType.SLIDING_WINDOW;
    }
}
